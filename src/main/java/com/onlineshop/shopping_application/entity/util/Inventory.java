package com.onlineshop.shopping_application.entity.util;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Inventory {
    @JsonProperty("total")
    private long total;

    @JsonProperty("available")
    private long available;

    @JsonProperty("reserved")
    private long reserved;

    public Inventory() {
    }

    public Inventory(long total, long available, long reserved) {
        this.total = total;
        this.available = available;
        this.reserved = reserved;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getAvailable() {
        return available;
    }

    public void setAvailable(long available) {
        this.available = available;
    }

    public long getReserved() {
        return reserved;
    }

    public void setReserved(long reserved) {
        this.reserved = reserved;
    }
}
