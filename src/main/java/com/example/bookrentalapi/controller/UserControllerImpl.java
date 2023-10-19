package com.example.bookrentalapi.controller;

import com.example.bookrentalapi.controller.contract.UserController;
import com.example.bookrentalapi.dto.UserDTO;
import com.example.bookrentalapi.dto.mapper.UserMapper;
import com.example.bookrentalapi.model.User;
import com.example.bookrentalapi.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/user")
public class UserControllerImpl implements UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;
    private final ApiResponseBuilder<UserDTO, User> response;

    @Override
    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody User user) {
        return response.getResponseEntity(
                userMapper.toDto(userService.addUser(user)),
                HttpStatus.CREATED);
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return response.getResponseEntity(
                userMapper.toDto(userService.getUserById(id)),
                HttpStatus.OK);
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponsePage<UserDTO>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<User> pages = userService.getUsers(page, size);
        List<UserDTO> users = userMapper.toDto(pages.getContent());
        return response.getResponseEntity(users, pages, HttpStatus.OK);
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @RequestBody User user) {
        return response.getResponseEntity(
                userMapper.toDto(userService.updateUser(id, user)),
                HttpStatus.OK);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return response.getResponseEntity(HttpStatus.OK);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        userService.deleteAllUsers();
        return response.getResponseEntity(HttpStatus.OK);
    }
}
