package com.incetutku.ordermanagementsystem.order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record InventoryRequestDto(
        @NotBlank(message = "Inventory Name is required")
        String inventoryName,
        @Min(value = 1L, message = "The value must be greater than Zero")
        int qty
) {
}
