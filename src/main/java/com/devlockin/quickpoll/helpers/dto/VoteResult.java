package com.devlockin.quickpoll.helpers.dto;

import java.util.Collection;

public class VoteResult {

	private int totalVotes;
    private Collection<OptionCount> results;
    
    
    
	public VoteResult(int totalVotes, Collection<OptionCount> results) {
		super();
		this.totalVotes = totalVotes;
		this.results = results;
	}
	
	public int getTotalVotes() {
		return totalVotes;
	}
	public Collection<OptionCount> getResults() {
		return results;
	}
    
    
    
	
}
