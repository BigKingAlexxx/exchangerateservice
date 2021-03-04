package org.alexherr.exchangerateservice.model;

import java.time.LocalDate;

public class ConvertedCurrency {

    private LocalDate date;
    private String currencyShortName;
    private float result;
    private String base;

    public ConvertedCurrency(LocalDate date, String currencyShortName, float result, String base) {
        this.date = date;
        this.currencyShortName = currencyShortName;
        this.result = result;
        this.base = base;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCurrencyShortName() {
        return currencyShortName;
    }

    public float getResult() {
        return result;
    }

    public String getBase() {
        return base;
    }
}
