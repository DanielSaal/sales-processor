package com.dsa.salesprocessor.converter;

import com.dsa.salesprocessor.model.Sale;
import com.dsa.salesprocessor.model.Salesman;

import java.util.List;

public class SaleConverter {

    public static Sale toModel(String[] line, List<Salesman> sellers) {

        return Sale.builder()
                .saleId(Long.valueOf(line[1]))
                .saleItems(SaleItemConverter.toList(line[2]))
                .salesman(SaleConverter.findSalesmanByName(line[3], sellers))
                .build();
    }

    private static Salesman findSalesmanByName(String name, List<Salesman> sellers) {

        return sellers
                .stream()
                .filter(salesman -> name.equals(salesman.getName()))
                .findAny()
                .orElse(null);
    }
}
