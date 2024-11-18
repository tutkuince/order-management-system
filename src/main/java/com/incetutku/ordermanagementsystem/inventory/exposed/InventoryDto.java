package com.incetutku.ordermanagementsystem.inventory.exposed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryDto {

    private Long id;
    private String name;
    private String description;
    private long price;
}
