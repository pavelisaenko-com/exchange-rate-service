package com.pavelisaenko.exchangerateservice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class RSHBParser implements Parser{
    private Document document;

    public RSHBParser() {

        try {
            document = Jsoup.connect("https://coins.rshb.ru/ingots").get();
        } catch (IOException e) {
            System.out.printf("Jsoup connection error: %s", e);
        }
    }

    //надо привести парсинг страницы к порядку  1) получение по-отдельности блоков <div> от каждой карточки
    //                                          2) выделение из каждого блока элементов:
    //                                             количество и цена или нет в наличии
    public ArrayList<Item> parse(){
        Elements items = document.select("div.product-layout");
        ArrayList<Item> itemsList = new ArrayList<>();
        Item temp;

        Elements quantityAndType = items.select("div.attribute>div.attribute-value");

        for (int i = 0; i < items.size(); i++){
            temp = new Item( quantityAndType.get(i * 2).text(),
                    quantityAndType.get(i * 2 + 1).text(),
                    items.select("div.price-box>span").get(i).text());
            itemsList.add(temp);
        }

        return itemsList;
    }

}