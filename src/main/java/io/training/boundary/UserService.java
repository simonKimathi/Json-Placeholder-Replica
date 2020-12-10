package io.training.boundary;

import io.training.entity.User;

import java.util.Optional;

public interface UserService extends CrudAbstractBean<User,Long> {

    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByUsername(String username);
}
