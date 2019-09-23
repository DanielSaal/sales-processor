package com.dsa.salesprocessor.processor;

import com.dsa.salesprocessor.converter.CustomerConverter;
import com.dsa.salesprocessor.converter.SaleConverter;
import com.dsa.salesprocessor.converter.SalesmanConverter;
import com.dsa.salesprocessor.model.Customer;
import com.dsa.salesprocessor.model.Sale;
import com.dsa.salesprocessor.model.Salesman;
import com.dsa.salesprocessor.util.ProcessorUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static com.dsa.salesprocessor.enums.DataTypeEnum.*;

/**
 * Classe de processamento dos arquivos de entrada e saída.
 *
 * @author daniel.alves
 */
@Component
public class FileProcessor {

    private static final Logger LOGGER = Logger.getLogger(FileProcessor.class.getName());

    /**
     * Método que realiza o processamento dos arquivos de entrada e a geração do relatório.
     *
     * @param pathStream lista de arquivos para processamento.
     */
    public void processor(Stream<Path> pathStream) {

        LOGGER.info("Iniciando serviço de processamento dos arquivos de entrada e saída");
        List<Salesman> sellers = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        List<Sale> sales = new ArrayList<>();

        // INPUT PROCESSOR
        inputProcessor(pathStream, sellers, customers, sales);

        // OUTPUT PROCESSOR
        outputProcessor(sellers, customers, sales);

        LOGGER.info("Finalizado serviço de processamento dos arquivos de entrada e saída");

    }

    /**
     * Processamento dos arquivos de entrada.
     *
     * @param pathStream    lista de arquivos para processamento.
     * @param sellers       lista de {@link Salesman}.
     * @param customers     lista de {@link Customer}.
     * @param sales         lista de {@link Sale}.
     */
    private void inputProcessor(Stream<Path> pathStream, List<Salesman> sellers, List<Customer> customers, List<Sale> sales) {

        LOGGER.info("Iniciando processamento dos arquivos de entrada");
        pathStream.forEach(path -> {
            File file = path.toFile();
            LOGGER.info("Lendo arquivo " + file.getName());
            try {
                List<String> lines = FileUtils.readLines(file, "UTF-8");
                for (String line : lines) {
                    String[] info = line.split("ç");

                    if (info[0].equals(SALESMAN.getCode())) {
                        sellers.add(SalesmanConverter.toModel(info));

                    } else if (info[0].equals(CUSTOMER.getCode())) {
                        customers.add(CustomerConverter.toModel(info));

                    } else if (info[0].equals(SALE.getCode())) {
                        sales.add(SaleConverter.toModel(info, sellers));
                    }
                }
            } catch (Exception e) {
                LOGGER.severe("Erro no processamento do arquivo de entrada");
            }
        });
        LOGGER.info("Finalizado processamento dos arquivos de entrada");
    }

    /**
     * Processamento para geração do relatório de saída.
     *
     * @param sellers       lista de {@link Salesman}.
     * @param customers     lista de {@link Customer}.
     * @param sales         lista de {@link Sale}.
     */
    private void outputProcessor(List<Salesman> sellers, List<Customer> customers, List<Sale> sales) {

        LOGGER.info("Iniciando processamento do arquivo de saída");
        try {
            ProcessorUtils.writeOutputFile(customers.size(), sellers.size(), ProcessorUtils.findMostExpensiveSaleId(sales), ProcessorUtils.findWorstSalesman(sales));
        } catch (Exception e) {
            LOGGER.severe("Erro no processamento do arquivo de saída");
        }
        LOGGER.info("Finalizado processamento do arquivo de saída");
    }

}
