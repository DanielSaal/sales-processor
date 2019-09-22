package com.dsa.salesprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sale {

    private Long saleId;
    private List<SaleItem> saleItems;
    private Salesman salesman;

}
