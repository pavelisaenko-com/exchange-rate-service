package com.pavelisaenko.exchangerateservice;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

@Service
public class CSVExporter implements Exporter{

    private final String outputPath;

    public CSVExporter(){
        this.outputPath = "output.csv";
    }

    public void exportItems(ArrayList<Item> items) {

        Writer writer;

        try {
            writer = new FileWriter(outputPath, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StatefulBeanToCsv<Item> beanToCsv = new StatefulBeanToCsvBuilder<Item>(writer)
                .withApplyQuotesToAll(false)
                .build();

        try {
            if (new BufferedReader(new FileReader(outputPath)).readLine() == null){
                CSVWriter headerWriter;
                try {
                    headerWriter = new CSVWriter(new FileWriter(outputPath, false));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Field[] tableHeader = Item.class.getDeclaredFields();
                ArrayList<String> headerArr = new ArrayList<>();

                for (Field field : tableHeader) {
                    headerArr.add(field.getName());
                }
                headerWriter.writeNext(headerArr.toArray(new String[0]));
                headerWriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            beanToCsv.write(items);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            throw new RuntimeException(e);
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
