package com.dsa.salesprocessor.util;

import com.dsa.salesprocessor.model.Customer;
import com.dsa.salesprocessor.model.Sale;
import com.dsa.salesprocessor.model.Salesman;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Classe de utilidades do projeto.
 *
 * @author daniel.alves
 */
public class ProcessorUtils {

    private final static String BASE_DIRECTORY = System.getProperty("user.home") + File.separator + "data";
    public final static String INPUT_DIRECTORY = BASE_DIRECTORY + File.separator + "in";
    public final static String OUTPUT_DIRECTORY = BASE_DIRECTORY + File.separator + "out" + File.separator;

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
     * Busca o pior {@link Salesman}.
     *
     * @param sales lista de {@link Sale}.
     * @return nome do pior {@link Salesman}.
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

    /**
     * Escreve no arquivo out.txt os dados do relatório.
     *
     * @param quantityOfCustomers   quantidade de {@link Customer}.
     * @param quantityOfSellers     quantidade de {@link Salesman}
     * @param mostExpensiveSaleId   id da {@link Sale} mais cara.
     * @param nameOfWorstSalesman   nome do pior {@link Salesman}.
     *
     * @throws Exception exceção possível ao gerar relatório.
     */
    public static void writeOutputFile(Integer quantityOfCustomers, Integer quantityOfSellers, Long mostExpensiveSaleId, String nameOfWorstSalesman) throws Exception {

        File fout = new File(OUTPUT_DIRECTORY + "out.txt");
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        bw.write("Quantidade de clientes no arquivo de entrada: " + quantityOfCustomers);
        bw.newLine();
        bw.write("Quantidade de vendedores no arquivo de entrada: " + quantityOfSellers);
        bw.newLine();
        bw.write("ID da venda mais cara: " + mostExpensiveSaleId);
        bw.newLine();
        bw.write("O pior vendedor: " + nameOfWorstSalesman);
        bw.newLine();


        bw.close();
    }
}
