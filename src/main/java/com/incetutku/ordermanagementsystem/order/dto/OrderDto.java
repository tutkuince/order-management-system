package com.incetutku.ordermanagementsystem.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record OrderDto(
        @NotBlank(message = "Customer Name is required")
        String customerName,
        @NotBlank(message = "Customer Email is required")
        String customerEmail,
        @Valid
        List<InventoryRequestDto> inventoryRequestDtoList
) {
}
