package com.devlockin.quickpoll.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.devlockin.quickpoll.entities.Poll;

public interface PollRepository extends PagingAndSortingRepository<Poll, Long> {
}
