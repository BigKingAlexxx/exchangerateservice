package org.alexherr.exchangerateservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ExchangeRate {

    private String currencyShortName;
    private float rate;

    public ExchangeRate(String currencyShortName, float rate, int requests) {
        this.currencyShortName = currencyShortName;
        this.rate = rate;
    }

    @JsonIgnore
    public String getCurrencyShortName() {
        return currencyShortName;
    }

    public float getRate() {
        return rate;
    }
}
