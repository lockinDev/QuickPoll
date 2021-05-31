package com.devlockin.quickpoll.controllers;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devlockin.quickpoll.entities.Poll;
import com.devlockin.quickpoll.repositories.PollRepository;

@RestController
public class PollController {
	
	/*
	 * Is added Inject unless Autowired  
	 * to be more compliant.
	 * */
	@Inject
	private PollRepository pollRepository;
	
	
	@GetMapping("/polls")
	public ResponseEntity<Iterable<Poll>> getAllPolls(){
		Iterable<Poll> allPolls = pollRepository.findAll();
		return new ResponseEntity<>(allPolls, HttpStatus.OK);
	}
	
	
}
