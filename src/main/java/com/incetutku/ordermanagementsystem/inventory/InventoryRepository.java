package com.incetutku.ordermanagementsystem.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findInventoryByName(String name);

    List<Inventory> findInventoryByNameIn(List<String> names);
}
