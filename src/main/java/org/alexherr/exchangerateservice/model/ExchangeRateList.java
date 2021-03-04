package org.alexherr.exchangerateservice.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ExchangeRateList {
    private LocalDate date;
    private String base;
    private HashMap<String, ExchangeRate> rates;

    public ExchangeRateList(String date, String base, HashMap<String, ExchangeRate> rates) {
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

    public HashMap<String, ExchangeRate> getRates() {
        return rates;
    }
}
