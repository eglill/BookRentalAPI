package com.example.bookrentalapi.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
public class ReservationDTO {
    private Long id;
    private Long bookerUsername;
    private BookDTO book;
    private Date rentalBegins;
    private Date rentalEnds;
}
