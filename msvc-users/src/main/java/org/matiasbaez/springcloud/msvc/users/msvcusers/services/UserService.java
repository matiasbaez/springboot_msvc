package org.matiasbaez.springcloud.msvc.users.msvcusers.services;

import org.matiasbaez.springcloud.msvc.users.msvcusers.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> list();

    Optional<User> getById(Long id);

    User save(User user);

    void delete(Long id);

    Optional<User> getByEmail(String email);

    boolean existsByEmail(String email);

}
