package com.pavelisaenko.exchangerateservice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class BPParser implements Parser{
    private Document document;

    public ArrayList<Item> parse(){
        try {
            document = Jsoup.connect("https://www.primbank.ru/currency/").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Item> itemsList = new ArrayList<>();
        return itemsList;
    }
}
