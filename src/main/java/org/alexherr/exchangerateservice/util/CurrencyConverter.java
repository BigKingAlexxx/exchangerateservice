package org.alexherr.exchangerateservice.util;

import org.alexherr.exchangerateservice.ExchangerateserviceApplication;
import org.alexherr.exchangerateservice.model.ExchangeRateList;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class CurrencyConverter {

    public static BigDecimal getCurrencyAmount(float amount, String currencyFrom, String currencyTo, ExchangeRateList exchangeRateList) {
        BigDecimal rateFrom = BigDecimal.valueOf(exchangeRateList.getRates().get(currencyFrom));
        BigDecimal rateTo = BigDecimal.valueOf(exchangeRateList.getRates().get(currencyTo));
        return (rateTo.divide(rateFrom, 4, RoundingMode.HALF_EVEN)).multiply(new BigDecimal(amount));
    }

    public static BigDecimal getExchangeRate(String currencyFrom, String currencyTo, ExchangeRateList exchangeRateList) {
        BigDecimal rateFrom = BigDecimal.valueOf(exchangeRateList.getRates().get(currencyFrom));
        BigDecimal rateTo = BigDecimal.valueOf(exchangeRateList.getRates().get(currencyTo));
        return rateFrom.divide(rateTo, 4, RoundingMode.HALF_EVEN);
    }
}
