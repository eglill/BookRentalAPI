package com.example.bookrentalapi.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Rental {

    @Id
    @SequenceGenerator(
            name = "rental_sequence",
            sequenceName = "rental_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_sequence")
    @Column(name = "rental_id")
    private Long id;

    @NotNull(message = "Renter cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "renter_id", nullable = false, updatable = false)
    private User renter;

    @NotNull(message = "Book cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false, updatable = false)
    private Book book;

    // User who rents the book

    private boolean renterReceived;

    private boolean renterReturned;

    // User who lends the book

    private boolean lenderHandedOver;

    private boolean lenderReceived;

    @Column(nullable = false, updatable = false)
    private Date rentalBegins;

    @Column(nullable = false, updatable = false)
    private Date rentalEnds;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date created;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date updated;
}
