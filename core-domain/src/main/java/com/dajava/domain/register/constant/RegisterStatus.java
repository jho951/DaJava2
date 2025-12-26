package com.dajava.domain.register.constant;

public enum RegisterStatus {
	CREATED,     // 최초 생성
	ACTIVE,      // 유효 기간 내 활성
	SUSPENDED,   // 중단/일시정지
	EXPIRED      // 기간 만료
}