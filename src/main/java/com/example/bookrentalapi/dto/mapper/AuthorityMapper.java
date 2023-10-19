package com.example.bookrentalapi.dto.mapper;

import com.example.bookrentalapi.dto.AuthorityDTO;
import com.example.bookrentalapi.model.Authority;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class AuthorityMapper {

    ModelMapper modelMapper;

    @Autowired
    public AuthorityMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AuthorityDTO toDto(Authority authority) {
        return modelMapper.map(authority, AuthorityDTO.class);
    }

    public List<AuthorityDTO> toDto(List<Authority> authorities) {
        return authorities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
