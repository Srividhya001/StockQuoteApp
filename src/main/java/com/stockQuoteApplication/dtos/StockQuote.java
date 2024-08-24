package com.stockQuoteApplication.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StockQuote {

    @JsonProperty("Global Quote")
    private GlobalQuote globalQuote;
}
