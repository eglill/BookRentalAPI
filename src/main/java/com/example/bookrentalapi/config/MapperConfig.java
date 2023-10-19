package com.example.bookrentalapi.config;

import com.example.bookrentalapi.dto.BookDTO;
import com.example.bookrentalapi.dto.RentalDTO;
import com.example.bookrentalapi.dto.ReservationDTO;
import com.example.bookrentalapi.dto.UserDTO;
import com.example.bookrentalapi.model.Book;
import com.example.bookrentalapi.model.Rental;
import com.example.bookrentalapi.model.Reservation;
import com.example.bookrentalapi.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<Book, BookDTO> bookMap = modelMapper.createTypeMap(Book.class, BookDTO.class)
                .addMappings(
                        mapper -> mapper.map(src -> src.getOwner().getUsername(), BookDTO::setOwnerUsername)
                );

        TypeMap<Rental, RentalDTO> rentalMap = modelMapper.createTypeMap(Rental.class, RentalDTO.class)
                .addMappings(
                        mapper -> mapper.map(src -> src.getRenter().getUsername(), RentalDTO::setRenterUsername)
                );

        TypeMap<Reservation, ReservationDTO> reservationMap = modelMapper.createTypeMap(Reservation.class, ReservationDTO.class)
                .addMappings(
                        mapper -> mapper.map(src -> src.getBooker().getUsername(), ReservationDTO::setBookerUsername)
                );

        return modelMapper;
    }
}
