package com.dajava.domain.solution.exception;

import com.dajava.api.global.exception.ErrorCode;

public class SolutionException extends RuntimeException {
	public final ErrorCode errorCode;

	public SolutionException(final ErrorCode errorCode){
		super(errorCode.getDescription());
		this.errorCode = errorCode;
	}

}
