package com.dajava.api.exception;

import com.dajava.domain.common.DomainException;
import com.dajava.domain.common.DomainErrorCode;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 도메인 예외 → HTTP 응답으로 매핑
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DomainException.class)
	public ResponseEntity<ErrorResponse> handleDomainException(
		DomainException ex,
		HttpServletRequest request
	) {
		DomainErrorCode code = ex.getErrorCode();
		var status = ErrorCodeHttpMapper.resolve(code);
		var body = ErrorResponse.of(code.code(), ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(body);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAny(
		Exception ex,
		HttpServletRequest request
	) {
		var body = ErrorResponse.of("INTERNAL_ERROR", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.internalServerError().body(body);
	}
}
