package com.dsa.salesprocessor.processor;

import com.dsa.salesprocessor.converter.CustomerConverter;
import com.dsa.salesprocessor.converter.SaleConverter;
import com.dsa.salesprocessor.converter.SalesmanConverter;
import com.dsa.salesprocessor.model.Customer;
import com.dsa.salesprocessor.model.Sale;
import com.dsa.salesprocessor.model.Salesman;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.dsa.salesprocessor.enums.DataTypeEnum.*;

@Component
public class FileProcessor {

    public void processor(Stream<Path> pathStream) {

        List<Salesman> sellers = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        List<Sale> sales = new ArrayList<>();

        // INPUT PROCESSOR
        pathStream.forEach(path -> {
            File file = path.toFile();
            System.out.println("READING FILE  " + file.getName());
            try {
                List<String> lines = FileUtils.readLines(file, "UTF-8");
                for (String line : lines) {
                    System.out.println(line);
                    String[] info = line.split("ç");

                    if (info[0].equals(SALESMAN.getCode())) {
                        System.out.println("IT'S A SALESMAN");
                        sellers.add(SalesmanConverter.toModel(info));

                    } else if (info[0].equals(CUSTOMER.getCode())) {
                        System.out.println("IT'S A CUSTOMER");
                        customers.add(CustomerConverter.toModel(info));

                    } else if (info[0].equals(SALE.getCode())) {
                        System.out.println("IT'S A SALE");
                        sales.add(SaleConverter.toModel(info, sellers));
                    }
                }
            } catch (Exception e) {
                System.out.println("Input Processor Error: " + e.getMessage());
            }
        });

        // OUTPUT PROCESSOR
    }

}
