package org.alexherr.exchangerateservice;

import org.alexherr.exchangerateservice.api.ExchangeRateController;
import org.alexherr.exchangerateservice.model.ExchangeRate;
import org.alexherr.exchangerateservice.model.ExchangeRateList;
import org.alexherr.exchangerateservice.service.XMLService;
import org.alexherr.exchangerateservice.util.CurrencyConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest()
class ExchangerateserviceApplicationTests {

	@Autowired
	private ExchangeRateController exchangeRateController;

	@Test
	void contextLoads() {
		assertThat(exchangeRateController).isNotNull();
	}

	@Test
	void xmlLoads() throws Exception {
		assertThat(XMLService.getExchangeRateList()).isNotNull();
	}

	@Test
	void getCurrencyAmount() {
		HashMap<String, ExchangeRate> exchangeRateHashMap = new HashMap<>();
		exchangeRateHashMap.put("USD", new ExchangeRate("USD", 1.2f, 0));
		exchangeRateHashMap.put("EUR", new ExchangeRate("EUR", 1f, 0));
		ExchangeRateList exchangeRateList = new ExchangeRateList("2020-01-01", "EUR", exchangeRateHashMap);
		float amount = 5;
		float expected = amount / 1.2f;
		float actual = CurrencyConverter.getCurrencyAmount(amount, "USD", "EUR", exchangeRateList);
		assertThat(expected).isEqualTo(actual);
	}

	@Test
	void getExchangeRate() {
		HashMap<String, ExchangeRate> exchangeRateHashMap = new HashMap<>();
		exchangeRateHashMap.put("USD", new ExchangeRate("USD", 1.2f, 0));
		exchangeRateHashMap.put("EUR", new ExchangeRate("EUR", 1f, 0));
		ExchangeRateList exchangeRateList = new ExchangeRateList("2020-01-01", "EUR", exchangeRateHashMap);
		float expected = 1.2f;
		float actual = CurrencyConverter.getExchangeRate("USD", "EUR", exchangeRateList);
		assertThat(expected).isEqualTo(actual);
	}

}
