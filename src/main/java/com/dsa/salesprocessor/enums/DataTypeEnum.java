package com.dsa.salesprocessor.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum que representa o código dos possíveis dados presentes no arquivo para processamento.
 *
 * @author daniel.alves
 */
@Getter
@AllArgsConstructor
public enum DataTypeEnum {

    SALESMAN ("001"),
    CUSTOMER ("002"),
    SALE ("003");

    private String code;

}
