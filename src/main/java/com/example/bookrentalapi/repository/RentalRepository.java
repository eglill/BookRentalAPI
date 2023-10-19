package com.example.bookrentalapi.repository;

import com.example.bookrentalapi.model.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    Page<Rental> getRentalsByBookOwnerId(Long id, Pageable pageable);
    Page<Rental> getRentalsByRenterId(Long id, Pageable pageable);
}
