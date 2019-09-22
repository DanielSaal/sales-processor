package com.dsa.salesprocessor.converter;

import com.dsa.salesprocessor.model.Customer;

public class CustomerConverter {

    public static Customer toModel(String[] line) {

        return Customer.builder()
                .cnpj(line[1])
                .name(line[2])
                .businessArea(line[3])
                .build();
    }
}
