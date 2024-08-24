package com.stockQuoteApplication.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BatchQuote {

    @JsonProperty("bestMatches")
    private List<BatchGlobalQuote> bestMatches;


}
