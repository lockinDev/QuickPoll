package com.devlockin.quickpoll.repositories;

import org.springframework.data.repository.CrudRepository;

import com.devlockin.quickpoll.entities.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {
}


