package com.dajava.domain.event.exception;

import com.dajava.api.global.exception.ErrorCode;

public class MalformedHtmlNodeException  extends RuntimeException {

	public final ErrorCode errorCode;

	public MalformedHtmlNodeException(final ErrorCode errorCode) {
		super(errorCode.getDescription());
		this.errorCode = errorCode;
	}
}



