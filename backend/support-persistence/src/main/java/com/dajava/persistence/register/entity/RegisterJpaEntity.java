package com.dajava.persistence.register.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString(exclude = "passwordHash")
@EqualsAndHashCode(of = "serialNumber")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "registers")
public class RegisterJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false, updatable = false)
	private String serialNumber;

	@Setter @Column(nullable = false) private String email;
	@Setter @Column(nullable = false) private String passwordHash;
	@Setter @Column(nullable = false) private String url;
	@Setter @Column(nullable = false) private LocalDateTime startDate;
	@Setter @Column(nullable = false) private LocalDateTime endDate;
	@Setter @Column(nullable = false) private int durationDays;
	@Setter @Column(nullable = false) private String status;
	@Setter @Column(nullable = false) private boolean solutionComplete;

	// (선택) 정적 팩토리: 매핑 코드에서 쓰기 편함
	public static RegisterJpaEntity of(String serialNumber, String email, String passwordHash,
		String url, LocalDateTime start, LocalDateTime end,
		int durationDays, String status, boolean solutionComplete) {
		var e = new RegisterJpaEntity();
		e.serialNumber = serialNumber;
		e.email = email;
		e.passwordHash = passwordHash;
		e.url = url;
		e.startDate = start;
		e.endDate = end;
		e.durationDays = durationDays;
		e.status = status;
		e.solutionComplete = solutionComplete;
		return e;
	}
}
