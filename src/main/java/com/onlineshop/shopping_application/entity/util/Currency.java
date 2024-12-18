package com.onlineshop.shopping_application.entity.util;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Currency {
    @JsonProperty("currencyCode")
    String currencyCode;

    @JsonProperty("amount")
    double amount;

    public Currency() {
    }

    public Currency(String currencyCode, double amount) {
        this.currencyCode = currencyCode;
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
