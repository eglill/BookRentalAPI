package com.example.bookrentalapi.controller;

import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class ApiResponseBuilder<T, L> {

    public ResponseEntity<ResponsePage<T>> getResponseEntity(
            List<T> items, Page<L> pages, HttpStatus status) {
        ResponsePage<T> response = ResponsePage.<T>builder()
                .totalPages(pages.getTotalPages())
                .totalItems(pages.getTotalElements())
                .currentPage(pages.getNumber())
                .items(items)
                .build();
        return new ResponseEntity<>(response, status);
    }

    public ResponseEntity<T> getResponseEntity(
            T item, HttpStatus status) {
        return new ResponseEntity<>(item, status);
    }

    public ResponseEntity<HttpStatus> getResponseEntity(HttpStatus status) {
        return new ResponseEntity<>(status);
    }
}
