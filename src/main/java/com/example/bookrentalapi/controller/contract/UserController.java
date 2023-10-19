package com.example.bookrentalapi.controller.contract;

import com.example.bookrentalapi.controller.ResponsePage;
import com.example.bookrentalapi.dto.UserDTO;
import com.example.bookrentalapi.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface UserController {
    ResponseEntity<UserDTO> addUser(User user);
    ResponseEntity<UserDTO> getUserById(Long id);
    ResponseEntity<ResponsePage<UserDTO>> getUsers(int page, int size);
    ResponseEntity<UserDTO> updateUser(Long id, User user);
    ResponseEntity<HttpStatus> deleteUser(Long id);
    ResponseEntity<HttpStatus> deleteAllUsers();
}
