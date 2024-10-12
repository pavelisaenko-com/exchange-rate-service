package com.pavelisaenko.exchangerateservice;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Data
public class Item {
    @CsvBindByPosition(position = 0)
    private final String type;
    @CsvBindByPosition(position = 1)
    private final int quantity;
    @CsvBindByPosition(position = 2)
    private final int price;
    @CsvBindByPosition(position = 3)
    private final String dateTime;
    @CsvBindByPosition(position = 4)
    private final String bankName;

    public Item(String type, String quantity, String price, String bankName) {
        int quantityTmp;
        this.type = type;
        this.price = Integer.parseInt(price.replace(" ", ""));
        this.bankName = bankName;

        DateTimeFormatter dtf = new DateTimeFormatterBuilder().appendPattern("d.M.y H:m:s").toFormatter();
        this.dateTime = LocalDateTime.now().format(dtf);

        quantityTmp = Math.round(Float.parseFloat(quantity.split(" ")[0]));
        if (quantity.endsWith("кг")) {
            quantityTmp *= 1000;
        }
        this.quantity = quantityTmp;
    }
}
