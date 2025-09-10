package com.dajava.application.notification;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dajava.domain.notification.port.EmailSenderPort;

public class EmailNotificationService {
	private static final Logger log = LoggerFactory.getLogger(EmailNotificationService.class);
	private final EmailSenderPort emailSender;

	public EmailNotificationService(EmailSenderPort emailSender) {
		this.emailSender = emailSender;
	}

	public void sendRegisterCreateEmail(String to, String pageUrl, String serialNumber) {
		if (to == null || to.isBlank()) {
			log.info("수신자 이메일 주소가 제공되지 않았습니다.");
			return;
		}
		String subject = "DAJAVA 솔루션 신청 정보입니다.";
		String body = """
            귀하가 신청하신 서비스 내용은 다음과 같습니다.
            대상 URL = %s
            조회 일련번호 = %s
            """.formatted(pageUrl, serialNumber);

		emailSender.send(to, subject, body); // ★ 포트 호출
	}

	public void sendSolutionCompleteEmail(String to, String pageUrl, String serialNumber) {
		String subject = "DAJAVA 솔루션 완료 알림입니다.";
		String body = """
            귀하가 신청하신 서비스의 수집 기간 및 솔루션 데이터 생성이 완료되었습니다.
            대상 URL = %s
            조회 일련번호 = %s
            """.formatted(pageUrl, serialNumber);

		emailSender.send(to, subject, body); // ★ 포트 호출
	}
}
