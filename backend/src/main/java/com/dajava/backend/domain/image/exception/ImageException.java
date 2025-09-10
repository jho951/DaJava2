package com.dajava.api.domain.image.exception;

import com.dajava.api.global.exception.ErrorCode;

public class ImageException extends RuntimeException {
	public final ErrorCode errorCode;

	public ImageException(final ErrorCode errorCode) {
		super(errorCode.getDescription());
		this.errorCode = errorCode;
	}
}
