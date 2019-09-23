package com.dsa.salesprocessor.converter;

import com.dsa.salesprocessor.model.SaleItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Serviço de conversão da linha do arquivo texto para a {@link List<SaleItem>}.
 *
 * @author daniel.alves
 */
public class SaleItemConverter {

    /**
     * Converter a linha de texto para a {@link List<SaleItem>}.
     *
     * @param saleItemsString linha de texto.
     * @return {@link List<SaleItem>}.
     */
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

    /**
     * Converte um item da linha de texto para {@link SaleItem}.
     * - [0] - Item ID
     * - [1] - Quantity
     * - [2] - Price
     *
     * @param item parte do texto da linha.
     * @return {@link SaleItem}.
     */
    private static SaleItem toModel(String[] item) {

        return SaleItem.builder()
                .itemId(Long.valueOf(item[0]))
                .quantity(Long.valueOf(item[1]))
                .price(Double.valueOf(item[2]))
                .build();
    }
}
