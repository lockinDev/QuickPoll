package com.devlockin.quickpoll.helpers.dto.errors;

public class ErrorDetail {
	
	private String title, description, developerMessage;
	private int status;
	private long timeStamp;
	
	public ErrorDetail(String title, String description, String developerMessage, int status, long timeStamp) {
		super();
		this.title = title;
		this.description = description;
		this.developerMessage = developerMessage;
		this.status = status;
		this.timeStamp = timeStamp;
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
