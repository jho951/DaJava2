package com.dajava.domain.register;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(exclude = "passwordHash")
public final class Register {
	private final Long id;
	private final String serialNumber;
	private final String email;
	private final String passwordHash;
	private final String url;
	private final LocalDateTime startDate;
	private final LocalDateTime endDate;
	private final int durationDays;
	private final RegisterStatus status;
	private final boolean solutionComplete;

	// 🔒 검증은 여기에 집중
	private Register(Long id, String serialNumber, String email, String passwordHash, String url,
		LocalDateTime startDate, LocalDateTime endDate,
		int durationDays, RegisterStatus status, boolean solutionComplete) {
		this.id = id;
		this.serialNumber = requireNotBlank(serialNumber, "serialNumber");
		this.email = requireNotBlank(email, "email");
		this.passwordHash = requireNotBlank(passwordHash, "passwordHash");
		this.url = requireNotBlank(url, "url");
		this.startDate = Objects.requireNonNull(startDate, "startDate");
		this.endDate = Objects.requireNonNull(endDate, "endDate");
		if (endDate.isBefore(startDate)) throw new IllegalArgumentException("endDate before startDate");
		this.durationDays = durationDays;
		this.status = Objects.requireNonNull(status, "status");
		this.solutionComplete = solutionComplete;
	}

	@Builder(builderMethodName = "builder")
	public static Register newRegister(
		String serialNumber,
		String email,
		String passwordHash,
		String url,
		LocalDateTime startDate,
		LocalDateTime endDate
	) {
		int days = Math.toIntExact(ChronoUnit.DAYS.between(startDate, endDate));
		return new Register(
			null, serialNumber, email, passwordHash, url,
			startDate, endDate,
			days,
			RegisterStatus.COMPLETED,
			false
		);
	}

	// 도메인 행위(상태 전이)는 새 인스턴스를 반환(불변 스타일)
	public Register withId(Long id) {
		return new Register(id, serialNumber, email, passwordHash, url,
			startDate, endDate, durationDays, status, solutionComplete);
	}

	public Register completeSolution() {
		if (solutionComplete) return this;
		return new Register(id, serialNumber, email, passwordHash, url,
			startDate, endDate, durationDays, status, true);
	}

	public Register updateEndDate(LocalDateTime newEndDate) {
		long diff = Math.abs(ChronoUnit.DAYS.between(endDate, newEndDate));
		if (diff > 7) throw new IllegalArgumentException("MODIFY_DATE_EXCEEDED");
		int delta = Math.toIntExact(ChronoUnit.DAYS.between(endDate, newEndDate));
		return new Register(id, serialNumber, email, passwordHash, url,
			startDate, newEndDate, durationDays + delta, status, solutionComplete);
	}

	private static String requireNotBlank(String v, String name) {
		if (v == null || v.isBlank()) throw new IllegalArgumentException(name + " is blank");
		return v;
	}
}
