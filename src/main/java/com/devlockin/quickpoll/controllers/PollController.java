package com.devlockin.quickpoll.controllers;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RestController;


import com.devlockin.quickpoll.repositories.PollRepository;

@RestController
public class PollController {
	
	/*
	 * Is added Inject unless Autowired  
	 * to be more compliant
	 * */
	@Inject
	private PollRepository pollRepository;
	
}
