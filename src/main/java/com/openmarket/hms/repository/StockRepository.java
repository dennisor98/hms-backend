package com.openmarket.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openmarket.hms.domain.InventoryItem;
import com.openmarket.hms.domain.Stock;

public interface StockRepository extends JpaRepository<Stock,String> {
  Optional<Stock> findByItem(InventoryItem item);
}
