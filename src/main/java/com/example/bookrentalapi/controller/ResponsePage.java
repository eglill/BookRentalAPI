package com.example.bookrentalapi.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePage<T> {
    private long totalItems;
    private int totalPages;
    private int currentPage;
    private List<T> items;
}
