package com.devlockin.quickpoll.repositories;

import org.springframework.data.repository.CrudRepository;

import com.devlockin.quickpoll.entities.Poll;

public interface PollRepository extends CrudRepository<Poll, Long> {
}
