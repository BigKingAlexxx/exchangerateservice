package org.alexherr.exchangerateservice.util;

import org.alexherr.exchangerateservice.ExchangerateserviceApplication;
import org.alexherr.exchangerateservice.model.ExchangeRateList;

public class CurrencyConverter {

    public static float getCurrencyAmount(float amount, String currencyFrom, String currencyTo, ExchangeRateList exchangeRateList) {
        float rateFrom = exchangeRateList.getRates().get(currencyFrom).getRate();
        float rateTo = exchangeRateList.getRates().get(currencyTo).getRate();
        return (rateTo / rateFrom) * amount;
    }

    public static float getExchangeRate(String currencyFrom, String currencyTo, ExchangeRateList exchangeRateList) {
        ExchangerateserviceApplication.exchangeRateList.getRates().get(currencyFrom).addOneRequest();
        ExchangerateserviceApplication.exchangeRateList.getRates().get(currencyTo).addOneRequest();
        float rateFrom = exchangeRateList.getRates().get(currencyFrom).getRate();
        float rateTo = exchangeRateList.getRates().get(currencyTo).getRate();
        return rateFrom / rateTo;
    }
}
