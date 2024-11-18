package com.incetutku.ordermanagementsystem.inventory;

import com.incetutku.ordermanagementsystem.inventory.exposed.InventoryDto;
import lombok.experimental.UtilityClass;

public final class InventoryUtil {

    private InventoryUtil() {
    }

    public static InventoryDto mapInventoryDto(Inventory inventory) {
        return InventoryDto.builder()
                .id(inventory.getId())
                .name(inventory.getName())
                .description(inventory.getDescription())
                .price(inventory.getPrice())
                .build();
    }
}
