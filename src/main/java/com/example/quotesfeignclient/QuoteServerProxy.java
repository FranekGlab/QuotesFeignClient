package com.example.quotesfeignclient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(value = "quoters-extended-server-client")
public interface QuoteServerProxy {

    @GetMapping("/apiWithRequestParam")
    Quote getQuoteByParam(@RequestParam Long id);

    @GetMapping("/apiWithHeader")
    List<Quote> getQuoteByHeader(@RequestHeader String requestId);

    @PostMapping("/api/quote")
    Quote postQuote(@RequestBody QuoteValue value);

    @DeleteMapping("/api/quote/{id}")
    void deleteQuote(@PathVariable Long id);



}
