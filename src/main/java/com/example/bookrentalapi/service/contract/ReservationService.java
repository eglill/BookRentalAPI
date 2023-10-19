package com.example.bookrentalapi.service.contract;

import com.example.bookrentalapi.model.Reservation;
import org.springframework.data.domain.Page;

public interface ReservationService {
    Reservation addReservation(Reservation reservation);
    Reservation getReservationById(Long id);
    Page<Reservation> getReservationsByBookOwnerId(Long id, int page, int size);
    Page<Reservation> getReservationsByRenterId(Long id, int page, int size);
    Page<Reservation> getReservations(int page, int size);
    Reservation updateReservation(Long id, Reservation reservation);
    void deleteReservation(Long id);
    void deleteAllReservations();
}
