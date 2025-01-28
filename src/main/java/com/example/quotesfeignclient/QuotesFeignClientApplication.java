package com.example.quotesfeignclient;

import feign.FeignException;
import feign.RetryableException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableFeignClients
@Log4j2
@RequiredArgsConstructor
public class QuotesFeignClientApplication {

    private final QuoteServerProxy quoteClient;

    public static void main(String[] args) {
        SpringApplication.run(QuotesFeignClientApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void makeRequestToQuoteServer() {
        try {
            log.info(quoteClient.postQuote(new QuoteValue(null, "I use arch btw")));
            quoteClient.deleteQuote(1L);
            log.info(quoteClient.getQuoteByParam(3L));
            log.info(quoteClient.getQuoteByHeader("quoters-extended"));
        } catch (FeignException.FeignClientException feignException) {
            log.error("Client exception: " + feignException.getMessage() + feignException.status());
        } catch (FeignException.FeignServerException feignException) {
            log.error("Server exception: " + feignException.getMessage() + feignException.status());
        } catch (RetryableException retryableException) {
            log.error("Retryable exception: " + retryableException.getMessage() + retryableException.status());
        } catch (FeignException feignException) {
            log.error("Feign exception: " + feignException.getMessage() + feignException.status());
        }
    }
}
