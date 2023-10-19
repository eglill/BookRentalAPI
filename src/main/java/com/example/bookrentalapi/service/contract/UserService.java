package com.example.bookrentalapi.service.contract;

import com.example.bookrentalapi.model.User;
import org.springframework.data.domain.Page;

public interface UserService {
    User addUser(User user);
    void addAuthorityToUser(String username, String authorityName);
    User getUserById(Long id);
    Page<User> getUsers(int page, int size);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    void deleteAllUsers();
}
