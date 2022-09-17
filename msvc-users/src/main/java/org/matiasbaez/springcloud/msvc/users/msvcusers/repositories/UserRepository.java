package org.matiasbaez.springcloud.msvc.users.msvcusers.repositories;

import org.matiasbaez.springcloud.msvc.users.msvcusers.models.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
