package com.dsa.salesprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleItem {

    private Long itemId;
    private Long quantity;
    private Double price;
}
