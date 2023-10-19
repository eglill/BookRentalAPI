package com.example.bookrentalapi.service.specification;

import lombok.*;

@Getter
@Setter
@Builder
public class Filter{

    private String field;
    private String value;

    public Filter(String field, String value) {
        this.field = field;
        this.value = value;
    }
}
