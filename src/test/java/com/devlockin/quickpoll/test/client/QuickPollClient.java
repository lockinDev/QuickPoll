package com.devlockin.quickpoll.test.client;

import java.net.URI;

import org.springframework.web.client.RestTemplate;

import com.devlockin.quickpoll.entities.Poll;

public class QuickPollClient {

	private static final String QUICK_POLL_URI_V1 = "http://localhost:8080/v1/polls";
	private RestTemplate restTemplate = new RestTemplate();

	public Poll getPollById(Long pollId) {
		return restTemplate.getForObject(QUICK_POLL_URI_V1 + "/{pollId}", Poll.class, pollId);
	}
	
	public URI createPoll(Poll poll) {
        return restTemplate.postForLocation( QUICK_POLL_URI_V1, poll);
	}

}
