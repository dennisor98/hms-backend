package com.openmarket.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openmarket.hms.domain.InventoryItem;

public interface InventoryItemRepository extends JpaRepository<InventoryItem,String>{

}
