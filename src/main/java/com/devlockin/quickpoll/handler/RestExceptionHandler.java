package com.devlockin.quickpoll.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devlockin.quickpoll.exceptions.ResourceNotFoundException;
import com.devlockin.quickpoll.helpers.dto.errors.ErrorDetail;
import com.devlockin.quickpoll.helpers.dto.errors.ValidationError;

@ControllerAdvice
public class RestExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe,
			HttpServletRequest request) {
		ErrorDetail errorDetail = new ErrorDetail("Resource Not Found", rnfe.getMessage(), rnfe.getClass().getName(),
				HttpStatus.NOT_FOUND.value(), new Date().getTime());
		return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException manve, HttpServletRequest request) {
		
		Map<String, List<ValidationError>> errors = new HashMap();
		List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
		for (FieldError fe : fieldErrors) {
			List<ValidationError> validationErrorList = errors.get(fe.getField());
			if (validationErrorList == null) {
				validationErrorList = new ArrayList<ValidationError>();

				errors.put(fe.getField(), validationErrorList);
			}
			ValidationError validationError = new ValidationError(fe.getCode(), fe.getDefaultMessage());
			validationErrorList.add(validationError);
		}
		
		ErrorDetail errorDetail = new ErrorDetail("Validation Failed", manve.getMessage(), manve.getClass().getName(),
				HttpStatus.BAD_REQUEST.value(), new Date().getTime(), errors);
		
		return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);
	}

}