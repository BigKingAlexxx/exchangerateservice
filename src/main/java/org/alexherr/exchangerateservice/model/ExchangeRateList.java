package org.alexherr.exchangerateservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.TreeMap;

public class ExchangeRateList {
    private LocalDate date;
    private String base;
    private TreeMap<String, Float> rates;

    public ExchangeRateList(String date, String base, TreeMap<String, Float> rates) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date, formatter);
        this.base = base;
        this.rates = rates;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getBase() {
        return base;
    }

    public TreeMap<String, Float> getRates() {
        return rates;
    }
}
