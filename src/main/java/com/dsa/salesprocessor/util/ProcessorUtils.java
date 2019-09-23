package com.dsa.salesprocessor.util;

import com.dsa.salesprocessor.model.Sale;
import com.dsa.salesprocessor.model.Salesman;

import java.io.File;
import java.util.List;

/**
 * Classe de utilidades do projeto.
 *
 * @author daniel.alves
 */
public class ProcessorUtils {

    private final static String BASE_DIRECTORY = System.getProperty("user.home") + File.separator + "data";
    public final static String INPUT_DIRECTORY = BASE_DIRECTORY + File.separator + "in";
    public final static String OUTPUT_DIRECTORY = BASE_DIRECTORY + File.separator + "out";

    /**
     * Busca o objeto {@link Salesman} pelo nome.
     *
     * @param name nome do {@link Salesman}.
     * @param sellers lista de {@link Salesman}.
     *
     * @return {@link Salesman}
     */
    public static Salesman findSalesmanByName(String name, List<Salesman> sellers) {

        return sellers
                .stream()
                .filter(salesman -> name.equals(salesman.getName()))
                .findAny()
                .orElse(null);
    }

    /**
     * Busca o ID da {@link Sale} mais cara.
     *
     * @param sales lista de {@link Sale}.
     * @return id da {@link Sale}.
     */
    public static Long findMostExpensiveSaleId(List<Sale> sales) {

        Long id = 0L;
        Double mostExpensive = 0D;
        for(Sale sale : sales) {

           Double expensive = sale.getSaleItems().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();

           if(expensive > mostExpensive){
               id = sale.getSaleId();
               mostExpensive = expensive;
           }
        }

        return id;
    }

    /**
     * Busca o pior {@link com.dsa.salesprocessor.model.Salesman}.
     *
     * @param sales lista de {@link Sale}.
     * @return nome do pior {@link com.dsa.salesprocessor.model.Salesman}.
     */
    public static String findWorstSalesman(List<Sale> sales) {

        String name = "";
        Double lessExpensive = 0D;
        for(Sale sale : sales) {

            Double expensive = sale.getSaleItems().stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();

            if(lessExpensive == 0D || expensive < lessExpensive){
                name = sale.getSalesman().getName();
                lessExpensive = expensive;
            }
        }

        return name;
    }
}
