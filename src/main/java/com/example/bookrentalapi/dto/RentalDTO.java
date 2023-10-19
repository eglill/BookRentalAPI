package com.example.bookrentalapi.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@ToString
@Data
public class RentalDTO {
    private Long id;
    private String renterUsername;
    private BookDTO book;
    private Date rentalBegins;
    private Date rentalEnds;
    private boolean renterReceived;
    private boolean renterReturned;
    private boolean lenderHandedOver;
    private boolean lenderReceived;
}
