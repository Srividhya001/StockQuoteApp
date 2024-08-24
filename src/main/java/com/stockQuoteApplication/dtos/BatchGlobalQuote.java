package com.stockQuoteApplication.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BatchGlobalQuote {


    @JsonProperty("1. symbol")
    private String symbol;

    @JsonProperty("2. name")
    private String name;
    @JsonProperty("3. type")
    private String type;
    @JsonProperty("4. region")
    private String region;

    @JsonProperty("5. marketOpen")
    private String marketOpen;

    @JsonProperty("6. marketClose")
    private String marketClose;

    @JsonProperty("7. timezone")
    private String timezone;

    @JsonProperty("8. currency")
    private String currency;

    @JsonProperty("9. matchScore")
    private String matchScore;


    @JsonIgnore
    public boolean isEmpty() {
        return (symbol == null || symbol.isEmpty()) && (name == null || name.isEmpty()) &&
                (type == null || type.isEmpty()) && (region == null || region.isEmpty()) &&
                (marketClose == null || marketClose.isEmpty()) && (marketOpen == null || marketOpen.isEmpty()) &&
                (timezone == null || timezone.isEmpty()) && (currency == null || currency.isEmpty()) &&
                (matchScore == null || matchScore.isEmpty());
    }
}

