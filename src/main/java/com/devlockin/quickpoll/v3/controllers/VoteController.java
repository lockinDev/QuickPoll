package com.devlockin.quickpoll.v3.controllers;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devlockin.quickpoll.entities.Vote;
import com.devlockin.quickpoll.repositories.VoteRepository;

@RestController("voteControllerV2")
@RequestMapping("/v2")
public class VoteController {

	@Inject
	private VoteRepository voteRepository;
	
	@GetMapping("/polls/{pollId}/votes")
	public Iterable<Vote> getAllVotes(@PathVariable Long pollId) {
	        return voteRepository. findByPoll(pollId);
	}

	@PostMapping("/polls/{pollId}/votes")
	public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote vote) {
		vote = voteRepository.save(vote);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vote.getId()).toUri());
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}
	

}
