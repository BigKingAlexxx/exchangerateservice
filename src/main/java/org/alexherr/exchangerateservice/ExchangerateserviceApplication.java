package org.alexherr.exchangerateservice;

import org.alexherr.exchangerateservice.model.ExchangeRateList;
import org.alexherr.exchangerateservice.service.XMLService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@SpringBootApplication
@EnableScheduling
public class ExchangerateserviceApplication {

	public static ExchangeRateList exchangeRateList;

	public static void main(String[] args) {
		SpringApplication.run(ExchangerateserviceApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	@Scheduled(cron = "0 01 16 * * MON-FRI")
	public void getDataFromXML() throws ParserConfigurationException, SAXException, IOException {
		exchangeRateList = XMLService.getExchangeRateList();
	}
}
