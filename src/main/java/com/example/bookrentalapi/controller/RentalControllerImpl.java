package com.example.bookrentalapi.controller;

import com.example.bookrentalapi.controller.contract.RentalController;
import com.example.bookrentalapi.dto.RentalDTO;
import com.example.bookrentalapi.dto.mapper.RentalMapper;
import com.example.bookrentalapi.model.Rental;
import com.example.bookrentalapi.service.RentalServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/rental")
public class RentalControllerImpl implements RentalController {

    private final RentalServiceImpl rentalService;
    private final RentalMapper rentalMapper;
    private final ApiResponseBuilder<RentalDTO, Rental> response;

    @Override
    @PostMapping
    public ResponseEntity<RentalDTO> addRental(@RequestBody Rental rental) {
        return response.getResponseEntity(
                rentalMapper.toDto(rentalService.addRental(rental)),
                HttpStatus.CREATED);
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<RentalDTO> getRentalById(@PathVariable Long id) {
        return response.getResponseEntity(
                rentalMapper.toDto(rentalService.getRentalById(id)),
                HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/loaner/{id}")
    public ResponseEntity<ResponsePage<RentalDTO>> getRentalsByBookOwnerId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Rental> pages = rentalService.getRentalsByBookOwnerId(id, page, size);
        List<RentalDTO> rentals = rentalMapper.toDto(pages.getContent());
        return response.getResponseEntity(rentals, pages, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/renter/{id}")
    public ResponseEntity<ResponsePage<RentalDTO>> getRentalsByRenterId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Rental> pages = rentalService.getRentalsByRenterId(id, page, size);
        List<RentalDTO> rentals = rentalMapper.toDto(pages.getContent());
        return response.getResponseEntity(rentals, pages, HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponsePage<RentalDTO>> getRentals(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Rental> pages = rentalService.getRentals(page, size);
        List<RentalDTO> rentals = rentalMapper.toDto(pages.getContent());
        return response.getResponseEntity(rentals, pages, HttpStatus.OK);
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<RentalDTO> updateRental(
            @PathVariable Long id,
            @RequestBody Rental rental) {
        return response.getResponseEntity(
                rentalMapper.toDto(rentalService.updateRental(id, rental)),
                HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        return response.getResponseEntity(HttpStatus.OK);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllRentals() {
        rentalService.deleteAllRentals();
        return response.getResponseEntity(HttpStatus.OK);
    }
}
