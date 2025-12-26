package com.dajava.api.error;

import com.dajava.domain.common.DomainException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private final ErrorResolver resolver;

	public GlobalExceptionHandler(ErrorResolver resolver) {
		this.resolver = resolver;
	}

	@ExceptionHandler(DomainException.class)
	public ResponseEntity<ErrorResponse> handleDomain(DomainException ex, HttpServletRequest req) {
		String code = ex.code().name();
		HttpStatus status = resolver.resolveStatus(code);
		String message = resolver.resolveMessage(code, ex.getMessage());
		return ResponseEntity.status(status)
			.body(ErrorResponse.of(code, message, req.getRequestURI()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleOther(Exception ex, HttpServletRequest req) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(ErrorResponse.of("INTERNAL_SERVER_ERROR", ex.getMessage(), req.getRequestURI()));
	}
}
