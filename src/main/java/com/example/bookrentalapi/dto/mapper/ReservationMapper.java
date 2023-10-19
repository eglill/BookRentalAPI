package com.example.bookrentalapi.dto.mapper;

import com.example.bookrentalapi.dto.ReservationDTO;
import com.example.bookrentalapi.dto.mapper.contract.BaseMapper;
import com.example.bookrentalapi.model.Reservation;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class ReservationMapper implements BaseMapper<ReservationDTO, Reservation> {

    ModelMapper modelMapper;

    public ReservationMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ReservationDTO toDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDTO.class);
    }

    public List<ReservationDTO> toDto(List<Reservation> reservations) {
        return reservations.stream().map(this::toDto).collect(Collectors.toList());
    }
}