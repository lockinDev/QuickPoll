package com.devlockin.quickpoll.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Poll {

	@Id
	@GeneratedValue
	@Column(name = "POLL_ID")
	private Long id;
	
	@Column(name = "QUESTION")
	private String question;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "POLL_ID")
	private Set<Option> options;

	public Poll(String question) {
		super();
		this.question = question;
	}
	
	public Poll(String question, Set<Option> options) {
		super();
		this.question = question;
		this.options = options;
	}

	public Long getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public Set<Option> getOptions() {
		return options;
	}
	
	
	
}
