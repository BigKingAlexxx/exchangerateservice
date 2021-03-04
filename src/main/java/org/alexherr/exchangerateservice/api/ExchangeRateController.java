package org.alexherr.exchangerateservice.api;

import org.alexherr.exchangerateservice.ExchangerateserviceApplication;
import org.alexherr.exchangerateservice.model.ConvertedCurrency;
import org.alexherr.exchangerateservice.util.CurrencyConverter;
import org.alexherr.exchangerateservice.model.ExchangeRateList;
import org.alexherr.exchangerateservice.model.SingleRate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeRateController {

    @GetMapping("/latest")
    public ExchangeRateList getExchangeRateList() {
        return ExchangerateserviceApplication.exchangeRateList;
    }

    @GetMapping(value = "/latest", params = "symbols")
    public ResponseEntity getSingleRate(@RequestParam String symbols) {
        String[] currencies = symbols.split(",");
        String currencyFrom = currencies[0];
        String currencyTo = currencies[1];

        try {
            float rate = CurrencyConverter.getExchangeRate(currencyFrom, currencyTo, ExchangerateserviceApplication.exchangeRateList);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new SingleRate(ExchangerateserviceApplication.exchangeRateList.getDate(),
                            currencyFrom,
                            rate,
                            currencyTo));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in symbols");
        }
    }

    @GetMapping(value = "/convert", params = "symbols")
    public ResponseEntity getConvertedAmount(@RequestParam String symbols) {
        String[] symbolsArray = symbols.split(",");

        try {
            float amount = Float.parseFloat(symbolsArray[0]);
            String currencyFrom = symbolsArray[1];
            String currencyTo = symbolsArray[2];

            float result = CurrencyConverter.getCurrencyAmount(amount, currencyFrom, currencyTo, ExchangerateserviceApplication.exchangeRateList);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ConvertedCurrency(ExchangerateserviceApplication.exchangeRateList.getDate(),
                            currencyFrom, result, currencyTo));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in symbols");
        }
    }

    @GetMapping(value = "/chart", params = "symbols")
    public ResponseEntity getChart(@RequestParam String symbols) {
        String[] symbolsArray = symbols.split(",");

        try {
            String currencyFrom = symbolsArray[0];
            String currencyTo = symbolsArray[1];

            return ResponseEntity.status(HttpStatus.OK)
                    .body("<HTML><body> <a href=\"https://www.tradingview.com/symbols/" + currencyFrom
                            + currencyTo + "\">Outbound link to chart from tradingview.com</a></body></HTML>");
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in symbols");
        }
    }
}
