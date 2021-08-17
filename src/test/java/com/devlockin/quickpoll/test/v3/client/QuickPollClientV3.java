package com.devlockin.quickpoll.test.v3.client;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.devlockin.quickpoll.entities.Poll;

public class QuickPollClientV3 {

	private static final String QUICK_POLL_URI_3 = "http://localhost:8080/v3/polls";
	private RestTemplate restTemplate = new RestTemplate();


	private HttpHeaders getAuthenticationHeader(String username, String password) {
		String credentials = username + ":" + password;
		byte[] base64CredentialData = Base64.encodeBase64(credentials.getBytes());
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic " + new String(base64CredentialData));
		return headers;
	}

}
