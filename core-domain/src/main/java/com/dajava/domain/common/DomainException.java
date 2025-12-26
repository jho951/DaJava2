package com.dajava.domain.common;

/**
 * 도메인 계층에서 던지는 예외.
 * 프레임워크/전송계층에 독립적이어야 합니다.
 */
public class DomainException extends RuntimeException {
	private final ErrorCode code;

	public DomainException(ErrorCode code, String message) {
		super(message);
		this.code = code;
	}

	public ErrorCode code() {
		return code;
	}
}
