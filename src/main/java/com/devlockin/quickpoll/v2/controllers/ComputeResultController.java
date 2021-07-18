package com.devlockin.quickpoll.v2.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devlockin.quickpoll.entities.Vote;
import com.devlockin.quickpoll.helpers.dto.OptionCount;
import com.devlockin.quickpoll.helpers.dto.VoteResult;
import com.devlockin.quickpoll.repositories.VoteRepository;

@RestController("computeResultControllerV2")
@RequestMapping("/v2")
public class ComputeResultController {

	@Inject
	private VoteRepository voteRepository;

	@GetMapping("/computeresult")
	public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
		VoteResult voteResult = new VoteResult();
		Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
		voteResult = getVoteResult(allVotes);
		return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
	}
	
	
	private VoteResult getVoteResult(Iterable<Vote> allVotes) {
		int totalVotes = 0;
		Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();
		for(Vote v : allVotes) {
		        totalVotes ++;
		        OptionCount optionCount = tempMap.get(v.getOption().getId());
		        if(optionCount == null) {
		                optionCount = new OptionCount();
		                optionCount.setOptionId(v.getOption().getId());
		                tempMap.put(v.getOption().getId(), optionCount);
		        }
		        optionCount.setCount(optionCount.getCount()+1);
		}
		
		return new VoteResult(totalVotes, tempMap.values());
	}

}
