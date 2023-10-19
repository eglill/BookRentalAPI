package com.example.bookrentalapi.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class UserDTO {
    private Long id;
    private String username;
    private boolean enabled;
    private String firstName;
    private String lastName;
    private String email;
//    private List<BookDTO> books;
//    //    TODO
//    private List<RentalDTO> booksRented;
//    //    TODO
//    private List<RentalDTO> booksRentedByUser;
//    //    TODO
//    private List<BookDTO> rentedBooksOverdue;
//    //    TODO
//    private List<BookDTO> rentedByUserBooksOverdue;
    private List<AuthorityDTO> authorities;
}
