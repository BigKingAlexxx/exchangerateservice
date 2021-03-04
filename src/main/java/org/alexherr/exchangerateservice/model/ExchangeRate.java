package org.alexherr.exchangerateservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ExchangeRate {

    private String currencyShortName;
    private float rate;
    private int requests;

    public ExchangeRate(String currencyShortName, float rate, int requests) {
        this.currencyShortName = currencyShortName;
        this.rate = rate;
        this.requests = requests;
    }

    @JsonIgnore
    public String getCurrencyShortName() {
        return currencyShortName;
    }

    public float getRate() {
        return rate;
    }

    public int getRequests() {
        return requests;
    }
    public void addOneRequest() {
        this.requests += 1;
    }
}
