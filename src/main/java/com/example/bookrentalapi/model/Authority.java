package com.example.bookrentalapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Authority {

    @Id
    @SequenceGenerator(
            name = "authorities_sequence",
            sequenceName = "authorities_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authorities_sequence")
    @Column(name = "authorities_id")
    private Long id;

    private String authority;
}
