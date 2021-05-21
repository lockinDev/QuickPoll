package com.devlockin.quickpoll.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Option {

	@Id
	@GeneratedValue
	@Column(name = "OPTION_ID")
	private Long id;
	
	public Option(String value) {
		super();
		this.value = value;
	}
	
	@Column(name = "OPTION_VALUE")
	private String value;

	public Long getId() {
		return id;
	}

	public String getValue() {
		return value;
	}
	
	
	
	
}
