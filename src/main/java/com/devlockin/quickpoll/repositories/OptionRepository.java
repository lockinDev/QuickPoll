package com.devlockin.quickpoll.repositories;

import org.springframework.data.repository.CrudRepository;

import com.devlockin.quickpoll.entities.Option;

public interface OptionRepository extends CrudRepository<Option, Long> {
}
