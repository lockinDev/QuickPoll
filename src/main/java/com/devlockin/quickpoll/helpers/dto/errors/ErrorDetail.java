package com.devlockin.quickpoll.helpers.dto.errors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorDetail {
	
	private String title, description, developerMessage;
	private int status;
	private long timeStamp;
	private Map<String, List<ValidationError>> errors = new HashMap()();
	
	

	public ErrorDetail(String title, String description, String developerMessage, int status, long timeStamp,
			Map<String, List<ValidationError>> errors) {
		super();
		this.title = title;
		this.description = description;
		this.developerMessage = developerMessage;
		this.status = status;
		this.timeStamp = timeStamp;
		this.errors = errors;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public int getStatus() {
		return status;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

}
