package com.dajava.domain.common;

import lombok.Getter;

/**
 * 도메인 계층에서 던지는 예외.
 * 프레임워크/전송계층에 독립적이어야 합니다.
 */
@Getter
public class DomainException extends RuntimeException {
	private final DomainErrorCode errorCode;
	private final Object[] args;

	public DomainException(DomainErrorCode errorCode, Object... args) {
		super(errorCode.format(args)); // getMessage() 에는 포맷된 메시지 반환
		this.errorCode = errorCode;
		this.args = args;
	}
}
