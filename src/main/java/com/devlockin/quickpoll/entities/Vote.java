package com.devlockin.quickpoll.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vote {

	@Id
	@GeneratedValue
	@Column(name = "VOTE_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "OPTION_ID")
	private Option option;

	public Vote(Option option) {
		super();
		this.option = option;
	}

	public Long getId() {
		return id;
	}

	public Option getOption() {
		return option;
	}
	
	
	
}
