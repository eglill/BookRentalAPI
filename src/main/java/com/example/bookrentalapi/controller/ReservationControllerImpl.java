package com.example.bookrentalapi.controller;

import com.example.bookrentalapi.controller.contract.ReservationController;
import com.example.bookrentalapi.dto.ReservationDTO;
import com.example.bookrentalapi.dto.mapper.ReservationMapper;
import com.example.bookrentalapi.model.Reservation;
import com.example.bookrentalapi.service.ReservationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/reservation")
public class ReservationControllerImpl implements ReservationController {

    private final ReservationServiceImpl reservationService;
    private final ReservationMapper reservationMapper;
    private final ApiResponseBuilder<ReservationDTO, Reservation> response;

    @Override
    @PostMapping
    public ResponseEntity<ReservationDTO> addReservation(@RequestBody Reservation reservation) {
        return response.getResponseEntity(
                reservationMapper.toDto(reservationService.addReservation(reservation)),
                HttpStatus.CREATED);
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        return response.getResponseEntity(
                reservationMapper.toDto(reservationService.getReservationById(id)),
                HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/loaner/{id}")
    public ResponseEntity<ResponsePage<ReservationDTO>> getReservationsByBookOwnerId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Reservation> pages = reservationService.getReservationsByBookOwnerId(id, page, size);
        List<ReservationDTO> reservations = reservationMapper.toDto(pages.getContent());
        return response.getResponseEntity(reservations, pages, HttpStatus.OK);
    }

    @Override
    @GetMapping(path = "/renter/{id}")
    public ResponseEntity<ResponsePage<ReservationDTO>> getReservationsByRenterId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Reservation> pages = reservationService.getReservationsByRenterId(id, page, size);
        List<ReservationDTO> reservations = reservationMapper.toDto(pages.getContent());
        return response.getResponseEntity(reservations, pages, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponsePage<ReservationDTO>> getReservations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Reservation> pages = reservationService.getReservations(page, size);
        List<ReservationDTO> reservations = reservationMapper.toDto(pages.getContent());
        return response.getResponseEntity(reservations, pages, HttpStatus.OK);
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(
            @PathVariable Long id,
            @RequestBody Reservation reservation) {
        return response.getResponseEntity(
                reservationMapper.toDto(reservationService.updateReservation(id, reservation)),
                HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return response.getResponseEntity(HttpStatus.OK);

    }

    @Override
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllReservations() {
        reservationService.deleteAllReservations();
        return response.getResponseEntity(HttpStatus.OK);
    }
}
