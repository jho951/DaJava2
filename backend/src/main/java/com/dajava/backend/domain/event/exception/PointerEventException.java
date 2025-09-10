package com.dajava.api.domain.event.exception;

import com.dajava.api.global.exception.ErrorCode;

public class PointerEventException extends RuntimeException {
	public final ErrorCode errorCode;

	public PointerEventException(final ErrorCode errorCode) {
		super(errorCode.getDescription());
		this.errorCode = errorCode;
	}
}
