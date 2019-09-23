package com.dsa.salesprocessor.converter;

import com.dsa.salesprocessor.model.Salesman;

/**
 * Serviço de conversão da linha do arquivo de texto para a {@link Salesman}.
 *
 * @author daniel.alves
 */
public class SalesmanConverter {

    /**
     *  Converter a linha de texto para {@link Salesman}.
     *  - [1] - CPF
     *  - [2] - Name
     *  - [3] - Salary
     *
     * @param line linha do arquivo de texto.
     * @return {@link Salesman}
     */
    public static Salesman toModel(String[] line) {

        return Salesman.builder()
                .cpf(line[1])
                .name(line[2])
                .salary(Double.valueOf(line[3]))
                .build();
    }
}
