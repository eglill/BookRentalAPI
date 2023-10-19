package com.example.bookrentalapi.dto.mapper;

import com.example.bookrentalapi.dto.RentalDTO;
import com.example.bookrentalapi.dto.mapper.contract.BaseMapper;
import com.example.bookrentalapi.model.Rental;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class RentalMapper implements BaseMapper<RentalDTO, Rental> {

    ModelMapper modelMapper;

    public RentalMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RentalDTO toDto(Rental rental) {
        return modelMapper.map(rental, RentalDTO.class);
    }

    public List<RentalDTO> toDto(List<Rental> rentals) {
        return rentals.stream().map(this::toDto).collect(Collectors.toList());
    }
}
