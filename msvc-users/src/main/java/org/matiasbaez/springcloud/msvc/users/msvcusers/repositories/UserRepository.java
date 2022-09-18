package org.matiasbaez.springcloud.msvc.users.msvcusers.repositories;

import org.matiasbaez.springcloud.msvc.users.msvcusers.models.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    // By repository method
    Optional<User> findByEmail(String email);

    // By custom query
    // @Query("SELECT u FROM User u WHERE u.email = ?1")
    // Optional<User> findByEmail(String email);

    // By keyword (exists)
    boolean existsByEmail(String email);
}
