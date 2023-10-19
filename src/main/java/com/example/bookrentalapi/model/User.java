package com.example.bookrentalapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="\"user\"")
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @Column(name = "user_id")
    private Long id;

    @NotNull(message = "Username cannot be null")
    @Size(min = 4, max = 20)
    @Column(unique = true, nullable = false)
    private String username;

    @NotNull(message = "Username cannot be null")
    @Size(min = 4, max = 30, message = "Password must be between 4 and 30 characters")
    @Column(nullable = false)
    private String password;

    private boolean enabled;

    @NotNull(message = "First name cannot be null")
    @Column(nullable = false)
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email must be valid")
    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private List<Book> books;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "renter")
    private List<Rental> booksRented;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "booker")
    private List<Reservation> booksReserved;

//    @OneToMany
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "authorities_id")
    private List<Authority> authorities = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date created;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date updated;
}
