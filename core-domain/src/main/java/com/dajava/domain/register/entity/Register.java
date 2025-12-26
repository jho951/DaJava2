package com.dajava.domain.register.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

import com.dajava.domain.common.DomainException;
import com.dajava.domain.common.ErrorCode;
import com.dajava.domain.register.constant.RegisterStatus;

public final class Register {

	private final UUID id;                 // 영속 식별자(선택)
	private final String serialNumber;     // 도메인 자연키 성격
	private final String email;
	private final String passwordHash;
	private final String url;
	private final LocalDateTime startDate;
	private final LocalDateTime endDate;
	private final RegisterStatus status;
	private final boolean solutionComplete;

	private Register(
		UUID id,
		String serialNumber,
		String email,
		String passwordHash,
		String url,
		LocalDateTime startDate,
		LocalDateTime endDate,
		RegisterStatus status,
		boolean solutionComplete
	) {
		// null/blank 검증
		if (serialNumber == null || serialNumber.isBlank())
			throw new DomainException(ErrorCode.REGISTER_SERIAL_REQUIRED, "serialNumber is required");
		if (email == null || email.isBlank())
			throw new DomainException(ErrorCode.REGISTER_EMAIL_REQUIRED, "email is required");
		if (passwordHash == null || passwordHash.isBlank())
			throw new DomainException(ErrorCode.REGISTER_PASSWORD_REQUIRED, "passwordHash is required");
		if (url == null || url.isBlank())
			throw new DomainException(ErrorCode.REGISTER_URL_REQUIRED, "url is required");
		if (startDate == null)
			throw new DomainException(ErrorCode.INVALID_ARGUMENT, "startDate is null");
		if (endDate == null)
			throw new DomainException(ErrorCode.INVALID_ARGUMENT, "endDate is null");
		if (endDate.isBefore(startDate))
			throw new DomainException(ErrorCode.REGISTER_INVALID_PERIOD, "endDate must be after startDate");

		this.id = id;
		this.serialNumber = serialNumber;
		this.email = email;
		this.passwordHash = passwordHash;
		this.url = url;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = Objects.requireNonNull(status, "status");
		this.solutionComplete = solutionComplete;
	}

	/** 팩토리: 생성 규칙은 여기로 통일 */
	public static Register of(
		UUID id,
		String serialNumber,
		String email,
		String passwordHash,
		String url,
		LocalDateTime startDate,
		LocalDateTime endDate,
		RegisterStatus status,
		boolean solutionComplete
	) {
		return new Register(id, serialNumber, email, passwordHash, url, startDate, endDate, status, solutionComplete);
	}

	/**衍生값은 계산으로 제공 */
	public int durationDays() {
		return Math.toIntExact(ChronoUnit.DAYS.between(startDate, endDate));
	}

	/** 불변 스타일 상태 전이(예: id 부여) */
	public Register withId(UUID newId) {
		return new Register(newId, serialNumber, email, passwordHash, url, startDate, endDate, status, solutionComplete);
	}

	// 동등성: 팀 규칙에 맞춰 하나로 통일.
	// 자연키가 serialNumber라면 아래처럼; DB id만 쓰려면 id 기반으로만 구성.
	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Register other)) return false;
		return serialNumber.equals(other.serialNumber);
	}
	@Override public int hashCode() { return serialNumber.hashCode(); }

	// 접근자
	public UUID id() { return id; }
	public String serialNumber() { return serialNumber; }
	public String email() { return email; }
	public String passwordHash() { return passwordHash; }
	public String url() { return url; }
	public LocalDateTime startDate() { return startDate; }
	public LocalDateTime endDate() { return endDate; }
	public RegisterStatus status() { return status; }
	public boolean solutionComplete() { return solutionComplete; }
}
