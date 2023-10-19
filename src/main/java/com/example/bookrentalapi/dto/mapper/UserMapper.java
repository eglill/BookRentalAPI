package com.example.bookrentalapi.dto.mapper;

import com.example.bookrentalapi.dto.UserDTO;
import com.example.bookrentalapi.dto.mapper.contract.BaseMapper;
import com.example.bookrentalapi.model.User;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class UserMapper implements BaseMapper<UserDTO, User> {

    ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO toDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> toDto(List<User> users) {
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }
}
