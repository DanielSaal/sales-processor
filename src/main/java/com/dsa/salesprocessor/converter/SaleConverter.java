package com.dsa.salesprocessor.converter;

import com.dsa.salesprocessor.model.Sale;
import com.dsa.salesprocessor.model.Salesman;
import com.dsa.salesprocessor.util.ProcessorUtils;

import java.util.List;

/**
 * Serviço de conversão da linha do arquivo de texto para a {@link Sale}.
 *
 * @author daniel.alves
 */
public class SaleConverter {

    /**
     * Converter a linha de texto para {@link Sale}.
     * - [1] - Sale ID
     * - [2] - Sale Items
     * - [3] - Salesman
     *
     * @param line linha do arquivo de texto.
     * @param sellers lista de {@link Salesman}.
     *
     * @return {@link Sale}
     */
    public static Sale toModel(String[] line, List<Salesman> sellers) {

        return Sale.builder()
                .saleId(Long.valueOf(line[1]))
                .saleItems(SaleItemConverter.toList(line[2]))
                .salesman(ProcessorUtils.findSalesmanByName(line[3], sellers))
                .build();
    }


}
