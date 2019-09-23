package com.dsa.salesprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe modelo de um vendedor.
 *
 * @author daniel.alves
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Salesman {

    private String cpf;
    private String name;
    private Double salary;
}
