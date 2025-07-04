package com.openmarket.hms.services;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.openmarket.hms.domain.InventoryItem;
import com.openmarket.hms.domain.ItemCategory;
import com.openmarket.hms.domain.PatientSession;
import com.openmarket.hms.domain.Stock;
import com.openmarket.hms.domain.StockMovement;
import com.openmarket.hms.domain.User;
import com.openmarket.hms.repository.InventoryItemRepository;
import com.openmarket.hms.repository.ItemCategoryRepository;
import com.openmarket.hms.repository.ItemSubCategoryRepository;
import com.openmarket.hms.repository.StockMovementRepository;
import com.openmarket.hms.repository.StockRepository;
import com.openmarket.hms.requestDto.InventoryItemDto;

@Service
public class InventoryService {
	@Autowired
	private InventoryItemRepository inventoryRepository;
	@Autowired
	private ItemCategoryRepository categoryRepository;
	@Autowired
	private ItemSubCategoryRepository subcategoryRepository;
	@Autowired
	private StockRepository stockRepoistory;
	@Autowired
	private StockMovementRepository stockmvtRepoistory;
   public Object createItem(InventoryItemDto req) {
	   Optional<InventoryItem> itemOpt = this.inventoryRepository.findBySkuNumber(req.getSkuNumber());
	   
	   if(itemOpt.isPresent()) {
		   return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("success",false,"message","Item already exists"));
	   }
	   
	   Optional<ItemCategory> categoryOpt = this.categoryRepository.findById(req.getCategoryId());
	   if(categoryOpt.isEmpty()) {
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("success",false,"message","Invalid categoryId"));
	   }
	   
	   ItemCategory category = categoryOpt.get();
	   
	   
	   
	   InventoryItem itemBuild = InventoryItem.builder().buyingPrice(req.getBuyingPrice())
			   .description(req.getDescription()).imageName(req.getImageName()).category(category)
			   .name(req.getName()).sellingPrice(req.getSellingPrice()).skuNumber(req.getSkuNumber())
			   .build();
	   try {
		   this.inventoryRepository.save(itemBuild);
		   return ResponseEntity.status(HttpStatus.OK).body(Map.of("success",true,"message","Item added"));
	   }catch(Exception ex) {
		   ex.printStackTrace();
		  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success",false,"message","Error processing request"));
	   }
			   
   }
   
   public boolean reserveItemStock(PatientSession session,String itemId,Integer quantity) {
	   User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   Optional<InventoryItem> itemOpt =  this.inventoryRepository.findById(itemId);
	   if(itemOpt.isEmpty()) {
		   return false;
	   }
	   InventoryItem item = itemOpt.get();
	   Optional<Stock> stockOpt =  this.stockRepoistory.findByItem(item);
	   
	   //we assume the stock is 0
	   if(stockOpt.isEmpty()) {
		   return false;
	   }
	   
	   Stock stock = stockOpt.get();
	   Integer availableQuantity = stock.getAvailableQuantity();
	   Integer reservedQuantity = stock.getReservedQuantity();
	   
	   if(quantity > availableQuantity) {
		   return false;
	   }
	   
	   StockMovement stockMvt = StockMovement.builder()
			   .description("reserve of item for sale").item(item)
			   .newStock(availableQuantity - quantity).previousStock(availableQuantity)
			   .price(null).quantity(quantity).session(session).user(user)
			   .build();

	   try {
		   stock.setAvailableQuantity(availableQuantity - quantity);
		   stock.setReservedQuantity(quantity+reservedQuantity);
		   
		   this.stockRepoistory.save(stock);
		   this.stockmvtRepoistory.save(stockMvt);
		   return true;
	   }catch(Exception ex) {
		   ex.printStackTrace();
		   return false;
	   }
   }
   
   public void deductStock(PatientSession session,InventoryItem item,Integer quantity) {
	   User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   Optional<Stock> stockOpt =  this.stockRepoistory.findByItem(item);
	   
	   if(stockOpt.isEmpty()) {
		   return;
	   }
	   
	   Stock stock = stockOpt.get();
	   Integer reservedStock = stock.getReservedQuantity();
	   
	   
	   try {
		   stock.setReservedQuantity(reservedStock - quantity);
		   this.stockRepoistory.save(stock);	
		   return;
	   }catch(Exception ex) {
		   stock.setReservedQuantity(reservedStock - quantity);
		   Integer availableQuantity = stock.getAvailableQuantity();
		   stock.setAvailableQuantity(availableQuantity+quantity);
		   this.stockRepoistory.save(stock);
		   
		   StockMovement stockMvt = StockMovement.builder()
				   .description("return of reserved stock").item(item)
				   .newStock(stock.getAvailableQuantity() - quantity).previousStock(stock.getAvailableQuantity())
				   .price(null).quantity(quantity).session(session).user(user)
				   .build();
		   
		   this.stockmvtRepoistory.save(stockMvt);
		   ex.printStackTrace();
		   return;
	   }
   }
   
   
}
