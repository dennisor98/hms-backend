package com.openmarket.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openmarket.hms.annotations.CustomController;
import com.openmarket.hms.repository.InventoryItemRepository;
import com.openmarket.hms.requestDto.InventoryItemDto;
import com.openmarket.hms.services.InventoryService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CustomController
@RequestMapping("invetory")
@Tag(name="Inventory")
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;
     public Object createItem(@Valid @RequestBody InventoryItemDto req) {
    	 return this.inventoryService.createItem(req);
    	 
    	 
     }
}
