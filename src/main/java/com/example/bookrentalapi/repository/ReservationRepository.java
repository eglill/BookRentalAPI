package com.example.bookrentalapi.repository;

import com.example.bookrentalapi.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> getReservationsByBookOwnerId(Long id, Pageable pageable);
    @Query(value = "select r from Reservation r where r.booker.id = ?1")
    Page<Reservation> getReservationsByRenterId(Long id, Pageable pageable);
}
