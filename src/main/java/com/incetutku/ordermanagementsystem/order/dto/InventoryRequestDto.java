package com.incetutku.ordermanagementsystem.order.dto;

public record InventoryRequestDto(
        String inventoryName,
        int qty
) {
}
