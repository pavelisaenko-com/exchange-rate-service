package com.pavelisaenko.exchangerateservice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface Parser {
    ArrayList<Item> parse();
}