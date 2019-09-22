package com.dsa.salesprocessor.converter;

import com.dsa.salesprocessor.model.SaleItem;

import java.util.ArrayList;
import java.util.List;

public class SaleItemConverter {

    public static List<SaleItem> toList(String saleItemsString) {
        List<SaleItem> saleItems = new ArrayList<>();

        // REMOVE "[" and "]"
        saleItemsString = saleItemsString.substring(1, saleItemsString.length() -1);

        // SPLIT BY ","
        String[] items = saleItemsString.split(",");

        // FOR ITEM
        for(String item : items) {
            String[] itemValue = item.split("-");
            saleItems.add(SaleItemConverter.toModel(itemValue));
        }

        return saleItems;
    }

    private static SaleItem toModel(String[] item) {

        return SaleItem.builder()
                .itemId(Long.valueOf(item[0]))
                .quantity(Long.valueOf(item[1]))
                .price(Double.valueOf(item[2]))
                .build();
    }
}
