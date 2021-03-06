package org.alexherr.exchangerateservice;

import org.alexherr.exchangerateservice.api.ExchangeRateController;
import org.alexherr.exchangerateservice.model.ExchangeRate;
import org.alexherr.exchangerateservice.model.ExchangeRateList;
import org.alexherr.exchangerateservice.service.XMLService;
import org.alexherr.exchangerateservice.util.CurrencyConverter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.TreeMap;

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
		TreeMap<String, Float> exchangeRateTreeMap = new TreeMap<>();
		exchangeRateTreeMap.put("USD", 1.2f);
		exchangeRateTreeMap.put("EUR", 1f);
		ExchangeRateList exchangeRateList = new ExchangeRateList("2020-01-01", "EUR", exchangeRateTreeMap);
		float amount = 5;
		BigDecimal expected = BigDecimal.valueOf(amount).divide(new BigDecimal("1.2f"));
		BigDecimal actual = CurrencyConverter.getCurrencyAmount(amount, "USD", "EUR", exchangeRateList);
		assertThat(expected).isEqualTo(actual);
	}

	@Test
	void getExchangeRate() {
		TreeMap<String, Float> exchangeRateTreeMap = new TreeMap<>();
		exchangeRateTreeMap.put("USD", 1.2f);
		exchangeRateTreeMap.put("EUR", 1f);
		ExchangeRateList exchangeRateList = new ExchangeRateList("2020-01-01", "EUR", exchangeRateTreeMap);
		BigDecimal expected = new BigDecimal("1.2f");
		BigDecimal actual = CurrencyConverter.getExchangeRate("USD", "EUR", exchangeRateList);
		assertThat(expected).isEqualTo(actual);
	}

}
