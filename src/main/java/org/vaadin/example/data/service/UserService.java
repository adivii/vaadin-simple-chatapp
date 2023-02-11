package org.vaadin.example.data.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.vaadin.example.data.entity.User;
import org.vaadin.example.data.repository.UserRepository;

@Service
@Transactional
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }

    // https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html
    public Optional<User> getUser(String username) {
        return userRepository.findById(username);
    }
}
