package com.example.bookrentalapi.service;

import com.example.bookrentalapi.model.Book;
import com.example.bookrentalapi.model.Reservation;
import com.example.bookrentalapi.repository.ReservationRepository;
import com.example.bookrentalapi.service.contract.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final BookServiceImpl bookService;

    @Transactional
    @Override
    public Reservation addReservation(Reservation reservation) {
        Book book = bookService.getBookById(reservation.getBook().getId());
        LocalDateTime date = LocalDateTime.now();
        reservation.setRentalBegins(java.sql.Timestamp.valueOf(date));
        reservation.setRentalEnds(java.sql.Timestamp.valueOf(date.plusDays(book.getPeriod())));
        return reservationRepository.save(reservation);
    }

    @Transactional
    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.getReferenceById(id);
    }

    @Transactional
    @Override
    public Page<Reservation> getReservationsByBookOwnerId(Long id, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return reservationRepository.getReservationsByBookOwnerId(id, paging);
    }

    @Transactional
    @Override
    public Page<Reservation> getReservationsByRenterId(Long id, int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return reservationRepository.getReservationsByRenterId(id, paging);
    }

    @Transactional
    @Override
    public Page<Reservation> getReservations(int page, int size) {
        Pageable paging = PageRequest.of(page, size);
        return reservationRepository.findAll(paging);
    }

    @Transactional
    @Override
    public Reservation updateReservation(Long id, Reservation newReservation) {
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservation.setRentalBegins(newReservation.getRentalBegins());
                    reservation.setRentalEnds(newReservation.getRentalEnds());
                    return reservationRepository.save(reservation);
                })
                .orElseGet(() -> reservationRepository.save(newReservation));
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public void deleteAllReservations() {
        reservationRepository.deleteAll();
    }
}
