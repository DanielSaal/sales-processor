package com.dsa.salesprocessor.util;

import com.dsa.salesprocessor.model.Customer;
import com.dsa.salesprocessor.model.Sale;
import com.dsa.salesprocessor.model.Salesman;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessedFile {

    private List<Salesman> sellers;
    private List<Customer> customers;
    private List<Sale> sales;

}
