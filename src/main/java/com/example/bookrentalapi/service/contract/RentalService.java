package com.example.bookrentalapi.service.contract;

import com.example.bookrentalapi.model.Rental;
import org.springframework.data.domain.Page;

public interface RentalService {
    Rental addRental(Rental rental);
    Rental getRentalById(Long Id);
    Page<Rental> getRentalsByBookOwnerId(Long id, int page, int size);
    Page<Rental> getRentalsByRenterId(Long id, int page, int size);
    Page<Rental> getRentals(int page, int size);
    Rental updateRental(Long id, Rental rental);
    void deleteRental(Long id);
    void deleteAllRentals();
}
