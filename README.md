# Projeto Sales Processor
Serviço de processamento de arquivos de vendas e geração de relatório.

## Sobre
Este projeto foi desenvolvido utilizando como base o [WatchService](https://docs.oracle.com/javase/7/docs/api/java/nio/file/WatchService.html) do pacote java.nio.file do Java.

O serviço é disparado ao ocorrer um dos seguintes eventos nos arquivos da pasta in: Criação, deleção ou modificação.
OBS.: Os arquivos devem estar com o encoding UTF-8.

Quando ocorre algum destes eventos todos os arquivos são processados e é gerado o relatório de saída.

Desenvolvido com Java 8 e Spring Boot.

## Execução
Para executar este projeto deve-se:

##### 1 - Compilar o projeto com o maven:
```console
mvn clean install
```

##### 2 - Acessar a pasta target do projeto e executá-lo:
```console
java -jar salesprocessor-0.0.1-SNAPSHOT.jar 
```
