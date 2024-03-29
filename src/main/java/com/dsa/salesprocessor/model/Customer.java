package com.dsa.salesprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe modelo do cliente.
 *
 * @author daniel.alves
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    private String cnpj;
    private String name;
    private String businessArea;
}
