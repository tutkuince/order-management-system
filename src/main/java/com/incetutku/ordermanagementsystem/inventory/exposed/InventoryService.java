package com.incetutku.ordermanagementsystem.inventory.exposed;

import java.util.List;

public interface InventoryService {

    InventoryDto fetchInventoryByName(String name);

    List<InventoryDto> fetchAllInName(List<String> inventoryNames);
}
