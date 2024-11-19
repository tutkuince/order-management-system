package com.incetutku.ordermanagementsystem.inventory;

import com.incetutku.ordermanagementsystem.inventory.exposed.InventoryDto;

public final class InventoryUtil {

    private InventoryUtil() {
    }

    public static InventoryDto mapInventoryDto(Inventory inventory) {
        return new InventoryDto(
                inventory.getId(),
                inventory.getName(),
                inventory.getDescription(),
                inventory.getPrice()
        );
    }
}
