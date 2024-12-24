package com.onlineshop.productservice.entity.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Attribute {

    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private String value;
}
