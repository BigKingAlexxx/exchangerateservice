package org.alexherr.exchangerateservice.model;

import java.time.LocalDate;

public class SingleRate {

    private LocalDate date;
    private String currencyShortName;
    private float rate;
    private String base;

    public SingleRate(LocalDate date, String currencyShortName, float rate, String base) {
        this.date = date;
        this.currencyShortName = currencyShortName;
        this.rate = rate;
        this.base = base;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCurrencyShortName() {
        return currencyShortName;
    }

    public float getRate() {
        return rate;
    }

    public String getBase() {
        return base;
    }
}
