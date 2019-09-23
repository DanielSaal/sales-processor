package com.dsa.salesprocessor.converter;

import com.dsa.salesprocessor.model.Customer;

/**
 * Serviço de conversão da linha do arquivo de texto para a {@link Customer}.
 *
 * @author daniel.alves
 */
public class CustomerConverter {

    /**
     * Converter a linha de texto para {@link Customer}.
     * - [1] - CNPJ
     * - [2] - Name
     * - [3] - Business Area
     *
     * @param line linha do arquivo de texto.
     * @return {@link Customer}
     */
    public static Customer toModel(String[] line) {

        return Customer.builder()
                .cnpj(line[1])
                .name(line[2])
                .businessArea(line[3])
                .build();
    }
}
