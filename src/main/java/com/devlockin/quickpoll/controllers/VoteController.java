package com.devlockin.quickpoll.controllers;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RestController;

import com.devlockin.quickpoll.repositories.VoteRepository;

@RestController
public class VoteController {
	
	 @Inject
     private VoteRepository voteRepository;

}
