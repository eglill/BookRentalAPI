package com.example.bookrentalapi.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class Book {

    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
    @Column(name = "book_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner", nullable = false, updatable = false)
    private User owner;

    private boolean active;

    private String udk;

    @NotNull(message = "Title cannot be null")
    @Column(nullable = false)
    private String title;

    private String author;

    @NotNull(message = "Period cannot be null")
    @Column(nullable = false)
    private Integer period;

    private String notes;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private List<Rental> bookRent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private List<Reservation> bookReservation;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date created;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date updated;

}
