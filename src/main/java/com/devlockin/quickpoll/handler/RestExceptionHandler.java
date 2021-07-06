package com.devlockin.quickpoll.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.devlockin.quickpoll.exceptions.ResourceNotFoundException;
import com.devlockin.quickpoll.helpers.dto.errors.ErrorDetail;
import com.devlockin.quickpoll.helpers.dto.errors.ValidationError;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Inject
	private MessageSource messageSource;

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
			ValidationError validationError = new ValidationError(fe.getCode(), messageSource.getMessage(fe, null));
			validationErrorList.add(validationError);
		}

		ErrorDetail errorDetail = new ErrorDetail("Validation Failed", manve.getMessage(), manve.getClass().getName(),
				HttpStatus.BAD_REQUEST.value(), new Date().getTime(), errors);

		return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorDetail errorDetail = new ErrorDetail("Message Not Readable", ex.getMessage(), ex.getClass().getName(),
				status.value(), new Date().getTime());

		return handleExceptionInternal(ex, errorDetail, headers, status, request);
	}

}