package ru.aston.jpa.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aston.jpa.UserPersistService;
import ru.aston.model.User;
import ru.aston.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserPersistServiceImpl implements UserPersistService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userRepository.save(user);
    }

}
