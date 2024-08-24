package com.stockQuoteApplication.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GlobalQuote {

    @JsonProperty("01. symbol")
    private String symbol;

    @JsonProperty("02. open")
    private Double open;

    @JsonProperty("03. high")
    private Double high;

    @JsonProperty("04. low")
    private Double low;

    @JsonProperty("05. price")
    private Double price;

    @JsonProperty("06. volume")
    private Long volume;

    @JsonProperty("07. latest trading day")
    private LocalDate latestTradingDay;

    @JsonProperty("08. previous close")
    private Double previousClose;

    @JsonProperty("09. change")
    private Double change;

    @JsonProperty("10. change percent")
    private String changePercent;

    public boolean isEmpty() {
        return (symbol == null || symbol.isEmpty()) &&
                (open == null || open == 0.0) &&
                (high == null || high == 0.0) &&
                (low == null || low == 0.0) &&
                (price == null || price == 0.0) &&
                (volume == null || volume == 0L) &&
                (latestTradingDay == null) &&
                (previousClose == null || previousClose == 0.0) &&
                (change == null || change == 0.0) &&
                (changePercent == null || changePercent.isEmpty());
    }
}