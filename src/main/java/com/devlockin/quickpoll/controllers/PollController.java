package com.devlockin.quickpoll.controllers;

import java.net.URI;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@GetMapping("/polls/{pollId}")
	public ResponseEntity<?> getPoll(@PathVariable Long pollId) throws Exception {
	        Optional<Poll> poll = pollRepository.findById(pollId);
	        if(!poll.isPresent()) {
	                throw new Exception("Pool not found");
	        }
	        return new ResponseEntity<>(poll.get(), HttpStatus.OK);
	}
	
	@PostMapping("/polls")
	public ResponseEntity<?> createPoll(@RequestBody Poll poll) {
		poll = pollRepository.save(poll);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(poll.getId())
                                .toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}
	
	
}
