package com.stockQuoteApplication.service;


import com.stockQuoteApplication.Response.StockQuoteResponse;
import com.stockQuoteApplication.config.RestTemplateConfig;
import com.stockQuoteApplication.dtos.BatchQuote;
import com.stockQuoteApplication.dtos.StockQuote;
import com.stockQuoteApplication.exception.StockNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class StockQuoteService {

    @Autowired
    RestTemplateConfig restTemplate;

    @Value("${api.key}")
    private String apiKey;

    public String buildURL(String function, String keyOrSymbol, String type, String apiKey) {
        String baseUrl = "https://www.alphavantage.co/query";
        String url;
        if (type.equalsIgnoreCase("single")) {
            url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                    .queryParam("function", function)
                    .queryParam("symbol", keyOrSymbol)
                    .queryParam("apikey", apiKey)
                    .toUriString();
        } else {
            url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                    .queryParam("function", function)
                    .queryParam("keywords", keyOrSymbol)
                    .queryParam("apikey", apiKey)
                    .toUriString();
        }

        return url;
    }

    public ResponseEntity<Object> getQuoteBySymbol(String symbol) {
        //https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=IBM&apikey=demo

        String url = buildURL("GLOBAL_QUOTE", symbol, "single", apiKey);
        System.out.println("URL is" + url);

        try {
            ResponseEntity<StockQuote> responseAlpha = restTemplate.restTemplate().getForEntity(url, StockQuote.class);
            System.out.println("Global quote " + responseAlpha.getBody().getGlobalQuote());
            System.out.println(responseAlpha.getStatusCode());

            if (responseAlpha.getBody().getGlobalQuote().isEmpty()) {
                throw new StockNotFoundException("Requested symbol not found");

            } else {
                ResponseEntity<Object> response = StockQuoteResponse.responseBuilder("The stock Quote for the requested symbol", responseAlpha.getStatusCode(), responseAlpha.getBody());
                return response;
            }

        } catch (RestClientException e) {
            System.out.println("Error occurred: " + e.getMessage());
            throw new RuntimeException("Failed to fetch stock quote", e);
        }

    }


    public ResponseEntity<Object> getBatchQuote(String keyword) {
        //https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=tesco&apikey=demo
        String url = buildURL("SYMBOL_SEARCH", keyword, "keywords", apiKey);
        System.out.println("URL is" + url);

        try {
            ResponseEntity<BatchQuote> responseAlpha = restTemplate.restTemplate().getForEntity(url, BatchQuote.class);

            System.out.println(responseAlpha.getBody());

            System.out.println(responseAlpha.getStatusCode());

            if (responseAlpha.getBody().getBestMatches() == null) {
                throw new StockNotFoundException("Requested keyword not found");

            } else {
                ResponseEntity<Object> response = StockQuoteResponse.responseBuilder("The batch stock Quotes that match are above", responseAlpha.getStatusCode(), responseAlpha.getBody());
                return response;
            }

        } catch (RestClientException e) {
            System.out.println("Error occurred: " + e.getMessage());
            throw new RuntimeException("Failed to fetch batch stock quote", e);
        }

    }
}
