package com.dajava.domain.log.exception;

import com.dajava.api.global.exception.ErrorCode;

public class LogException extends RuntimeException {
	public final ErrorCode errorCode;

	public LogException(final ErrorCode errorCode){
		super(errorCode.getDescription());
		this.errorCode = errorCode;
	}
}
