package com.dajava.api.domain.event.exception;

import com.dajava.api.global.exception.ErrorCode;

public class AbusingBaseLineException extends RuntimeException {

	public final ErrorCode errorCode;

	public AbusingBaseLineException(final ErrorCode errorCode) {
		super(errorCode.getDescription());
		this.errorCode = errorCode;
	}
}
