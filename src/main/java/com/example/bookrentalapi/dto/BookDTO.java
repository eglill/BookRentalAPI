package com.example.bookrentalapi.dto;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class BookDTO {
    private Long bookId;
    private String ownerUsername;
    private String udk;
    private String title;
    private String author;
    private Integer period;
    private String notes;
    private byte[] image;
}
