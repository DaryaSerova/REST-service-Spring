package ru.aston.jpa;


import ru.aston.model.User;

import java.util.Optional;

public interface UserPersistService {

    User createUser(User user);

    Optional<User> findUserById(Long userId);

    void deleteUser(Long userId);

    void updateUser(User user);

}

