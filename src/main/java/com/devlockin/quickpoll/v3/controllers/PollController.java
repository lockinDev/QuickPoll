package com.devlockin.quickpoll.v3.controllers;

import java.net.URI;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devlockin.quickpoll.entities.Poll;
import com.devlockin.quickpoll.exceptions.ResourceNotFoundException;
import com.devlockin.quickpoll.helpers.dto.errors.ErrorDetail;
import com.devlockin.quickpoll.repositories.PollRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController("pollControllerV2")
@RequestMapping("/v2")
@Api(value = "polls", description = "Poll API")
public class PollController {

	/*
	 * Is added Inject unless Autowired to be more compliant.
	 */
	@Inject
	private PollRepository pollRepository;

	@RequestMapping(value = "/polls", method = RequestMethod.GET)
	@ApiOperation(value = "Retrieves all the polls", response = Poll.class, responseContainer = "List")
	public ResponseEntity<Page<Poll>> getAllPolls(Pageable pageable) {
		Page<Poll> allPolls = pollRepository.findAll(pageable);
		return new ResponseEntity<>(allPolls, HttpStatus.OK);
	}

	@ApiOperation(value = "Retrieves a Poll associated with the pollId", response = Poll.class)
	@GetMapping("/polls/{pollId}")
	public ResponseEntity<?> getPoll(@PathVariable Long pollId) throws Exception {
		return new ResponseEntity<>(verifyPoll(pollId), HttpStatus.OK);
	}

	@ApiOperation(value = "Creates a new Poll", notes = "The newly created poll Id will be sent in the location response header", response = Void.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Poll Created Successfully", response = Void.class),
			@ApiResponse(code = 500, message = "Error creating Poll", response = ErrorDetail.class) })
	@PostMapping("/polls")
	public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
		poll = pollRepository.save(poll);
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newPollUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(poll.getId())
				.toUri();
		responseHeaders.setLocation(newPollUri);
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/polls/{pollId}")
	public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId) {
		verifyPoll(pollId);
		pollRepository.save(poll);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/polls/{pollId}")
	public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
		verifyPoll(pollId);
		pollRepository.deleteById(pollId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private Poll verifyPoll(Long pollId) throws ResourceNotFoundException {
		Optional<Poll> poll = pollRepository.findById(pollId);
		if (!poll.isPresent()) {
			throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
		}
		return poll.get();
	}

}
