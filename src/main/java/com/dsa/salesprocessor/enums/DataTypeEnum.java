package com.dsa.salesprocessor.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataTypeEnum {

    SALESMAN ("001"),
    CUSTOMER ("002"),
    SALE ("003");

    private String code;

}
