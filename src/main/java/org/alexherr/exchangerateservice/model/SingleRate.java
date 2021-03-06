package org.alexherr.exchangerateservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SingleRate {

    private LocalDate date;
    private String currencyShortName;
    private BigDecimal rate;
    private String base;

    public SingleRate(LocalDate date, String currencyShortName, BigDecimal rate, String base) {
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

    public BigDecimal getRate() {
        return rate;
    }

    public String getBase() {
        return base;
    }
}
