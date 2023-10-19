package com.example.bookrentalapi.controller.contract;

import com.example.bookrentalapi.controller.ResponsePage;
import com.example.bookrentalapi.dto.ReservationDTO;
import com.example.bookrentalapi.model.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ReservationController {
    ResponseEntity<ReservationDTO> addReservation(Reservation reservation);
    ResponseEntity<ReservationDTO> getReservationById(Long id);
    ResponseEntity<ResponsePage<ReservationDTO>> getReservationsByBookOwnerId(Long id, int page, int size);
    ResponseEntity<ResponsePage<ReservationDTO>> getReservationsByRenterId(Long id, int page, int size);
    ResponseEntity<ResponsePage<ReservationDTO>> getReservations(int page, int size);
    ResponseEntity<ReservationDTO> updateReservation(Long id, Reservation reservation);
    ResponseEntity<HttpStatus> deleteReservation(Long id);
    ResponseEntity<HttpStatus> deleteAllReservations();
}
