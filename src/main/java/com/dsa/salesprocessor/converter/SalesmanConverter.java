package com.dsa.salesprocessor.converter;

import com.dsa.salesprocessor.model.Salesman;

public class SalesmanConverter {

    public static Salesman toModel(String[] line) {

        return Salesman.builder()
                .cpf(line[1])
                .name(line[2])
                .salary(Double.valueOf(line[3]))
                .build();
    }
}
