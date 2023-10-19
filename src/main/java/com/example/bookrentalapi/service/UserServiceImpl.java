package com.example.bookrentalapi.service;

import com.example.bookrentalapi.model.Authority;
import com.example.bookrentalapi.repository.AuthorityRepository;
import com.example.bookrentalapi.repository.UserRepository;
import com.example.bookrentalapi.model.User;
import com.example.bookrentalapi.service.contract.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public User addUser(User user) {
        log.info("Saving new user {} to the database", user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public void addAuthorityToUser(String username, String authorityName) {
        log.info("Adding role {} to user {}", authorityName, username);
        User user = userRepository.findByUsername(username);
        Authority authority = authorityRepository.findByAuthority(authorityName);
        user.getAuthorities().add(authority);
//        TODO: add function in userController
    }

    @Override
    public User getUserById(Long id) {
        log.info("Fetching user with id: {}", id);
        return userRepository.getReferenceById(id);
    }

    @Override
    public Page<User> getUsers(int page, int size) {
        log.info("Fetching users at page {}, with size {}", page, size);
        Pageable paging = PageRequest.of(page, size);
        return userRepository.findAll(paging);
    }

    @Override
    public User updateUser(Long id, User newUser) {
        log.info("Updating user {}", newUser.getUsername());
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    user.setEnabled(newUser.isEnabled());
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                })
                .orElseGet(() -> userRepository.save(newUser));
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAllUsers() {
        log.info("Deleting all users");
        userRepository.deleteAll();
    }
}