package com.dajava.domain.register.exception;

import com.dajava.api.global.exception.ErrorCode;

public class AdminException extends RuntimeException {
	public final ErrorCode errorCode;

	public AdminException(final ErrorCode errorCode) {
		super(errorCode.getDescription());
		this.errorCode = errorCode;
	}
}
