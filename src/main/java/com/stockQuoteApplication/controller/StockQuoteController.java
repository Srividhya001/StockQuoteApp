package com.stockQuoteApplication.controller;

import com.stockQuoteApplication.model.User;
import com.stockQuoteApplication.service.MyUserDetailsService;
import com.stockQuoteApplication.service.StockQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stockQuote")
public class StockQuoteController {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    StockQuoteService stockQuoteService;

    @GetMapping("/test")
    public String getTestValue() {
        return "Hello";
    }

    @GetMapping("/getQuote")
    public ResponseEntity<Object> getQuoteBySymbol(@RequestParam String symbol) {
        return stockQuoteService.getQuoteBySymbol(symbol);
    }

    @GetMapping(value = "/getAllQuote")
    public ResponseEntity<Object> getBatchQuote(@RequestParam String keyword) {

        return stockQuoteService.getBatchQuote(keyword);
    }

    @PostMapping("/registerUser")
    public User registerUser(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return myUserDetailsService.registerUser(user);
    }


}

