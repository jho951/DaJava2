package com.dajava.domain.notification.port;

/** 외부 메일 전송을 추상화한 도메인 포트 */
public interface EmailSenderPort {
	void send(String to, String subject, String htmlBody);
}
