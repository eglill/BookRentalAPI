package com.example.bookrentalapi.service;

import com.example.bookrentalapi.model.Book;
import com.example.bookrentalapi.model.Rental;
import com.example.bookrentalapi.repository.RentalRepository;
import com.example.bookrentalapi.service.contract.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final BookServiceImpl bookService;

    @Transactional
    @Override
    public Rental addRental(Rental rental) {
        Book book = bookService.getBookById(rental.getBook().getId());
        LocalDateTime date = LocalDateTime.now();
        rental.setRentalBegins(java.sql.Timestamp.valueOf(date));
        rental.setRentalEnds(java.sql.Timestamp.valueOf(date.plusDays(book.getPeriod())));
        return rentalRepository.save(rental);
    }

    @Transactional
    @Override
    public Rental getRentalById(Long id) {
        return rentalRepository.getReferenceById(id);
    }

    @Transactional
    @Override
    public Page<Rental> getRentalsByBookOwnerId(Long id, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return rentalRepository.getRentalsByBookOwnerId(id, paging);
    }

    @Transactional
    @Override
    public Page<Rental> getRentalsByRenterId(Long id, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return rentalRepository.getRentalsByRenterId(id, paging);
    }

    @Transactional
    @Override
    public Page<Rental> getRentals(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return rentalRepository.findAll(paging);
    }

    @Transactional
    @Override
    public Rental updateRental(Long id, Rental newRental) {
        return rentalRepository.findById(id)
                .map(rental -> {
                    rental.setRenter(newRental.getRenter());
                    rental.setBook(newRental.getBook());
                    rental.setRenterReceived(newRental.isRenterReceived());
                    rental.setRenterReturned(newRental.isRenterReturned());
                    rental.setLenderHandedOver(newRental.isLenderHandedOver());
                    rental.setLenderReceived(newRental.isLenderReceived());
                    rental.setRentalEnds(newRental.getRentalEnds());
                    return rentalRepository.save(rental);
                })
                .orElseGet(() -> rentalRepository.save(newRental));
    }

    @Override
    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }

    @Override
    public void deleteAllRentals() {
        rentalRepository.deleteAll();
    }
}
