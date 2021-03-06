package org.alexherr.exchangerateservice.service;

import org.alexherr.exchangerateservice.model.ExchangeRate;
import org.alexherr.exchangerateservice.model.ExchangeRateList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

public class XMLService {
    private static final String URL_XML = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";

    public static ExchangeRateList getExchangeRateList() throws IOException, SAXException, ParserConfigurationException {
        return parseExchangeRates(getNodeListFromXML());
    }

    private static NodeList getNodeListFromXML() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(URL_XML);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName("Cube");
    }

    private static ExchangeRateList parseExchangeRates(NodeList nodeList) {
        TreeMap<String, Float> rates = new TreeMap<>();
        String date = null;
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Element element = (Element) node;
            if (element.hasAttribute("time")) {
                date = element.getAttribute("time");
            }
            if (element.hasAttribute("currency")){
                rates.put(element.getAttribute("currency"), Float.parseFloat(element.getAttribute("rate")));

            }
        }
        rates.put("EUR", 1f);
        return new ExchangeRateList(date, "EUR", rates);
    }
}
