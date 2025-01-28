package com.example.quotesfeignclient;

public record Quote(String type, QuoteValue value) {

    @Override
    public String toString() {
        return "Type: " + type + " Id: " + value.id() + " Quote: " + value.quote() + "\n";
    }
}
