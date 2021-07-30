package com.devlockin.quickpoll.repositories;

import org.springframework.data.repository.CrudRepository;

import com.devlockin.quickpoll.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
        public User findByUsername(String username);
}
