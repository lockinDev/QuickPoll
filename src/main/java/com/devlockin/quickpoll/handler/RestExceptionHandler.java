package com.devlockin.quickpoll.handler;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devlockin.quickpoll.exceptions.ResourceNotFoundException;
import com.devlockin.quickpoll.helpers.dto.errors.ErrorDetail;

@ControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe,
			HttpServletRequest request) {
		ErrorDetail errorDetail = new ErrorDetail("Resource Not Found", rnfe.getMessage(),
				rnfe.getClass().getName(), HttpStatus.NOT_FOUND.value(),
				new Date().getTime());
		return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
	}
}