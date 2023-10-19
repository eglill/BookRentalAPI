package com.example.bookrentalapi.controller.contract;

import com.example.bookrentalapi.controller.ResponsePage;
import com.example.bookrentalapi.dto.RentalDTO;
import com.example.bookrentalapi.model.Rental;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface RentalController {
    ResponseEntity<RentalDTO> addRental(Rental rental);
    ResponseEntity<RentalDTO> getRentalById(Long id);
    ResponseEntity<ResponsePage<RentalDTO>> getRentalsByBookOwnerId(Long id, int page, int size);
    ResponseEntity<ResponsePage<RentalDTO>> getRentalsByRenterId(Long id, int page, int size);
    ResponseEntity<ResponsePage<RentalDTO>> getRentals(int page, int size);
    ResponseEntity<RentalDTO> updateRental(Long id, Rental rental);
    ResponseEntity<HttpStatus> deleteRental(Long id);
    ResponseEntity<HttpStatus> deleteAllRentals();
}
