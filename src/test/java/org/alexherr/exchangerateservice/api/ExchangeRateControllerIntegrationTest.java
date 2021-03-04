package org.alexherr.exchangerateservice.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ExchangeRateController.class)
class ExchangeRateControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getExchangeRateList() throws Exception {
        RequestBuilder request = get("/latest");
        mvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void getSingleRate() throws Exception {
        mvc.perform(get("/latest?symbols=USD,EUR")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currencyShortName").value("USD"))
                .andExpect(jsonPath("$.base").value("EUR"));
    }

    @Test
    void getConvertedAmount() throws Exception {
        mvc.perform(get("/convert?symbols=50,USD,EUR")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currencyShortName").value("USD"))
                .andExpect(jsonPath("$.result").isNumber())
                .andExpect(jsonPath("$.base").value("EUR"));
    }

    @Test
    void getChart() throws Exception {
        mvc.perform(get("/chart?symbols=USD,EUR")
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("<HTML><body> <a href=\"https://www.tradingview.com/symbols/USDEUR\">Outbound link to chart from tradingview.com</a></body></HTML>"));
    }
}