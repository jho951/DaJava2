package com.dajava.notification.mail;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

import com.dajava.domain.notification.port.EmailSenderPort;


/**
 * EmailSenderPort 구현체.
 * Spring JavaMailSender를 사용해 HTML 메일을 비동기로 전송한다.
 */
@Service
public class AsyncEmailSender implements EmailSenderPort {

	private static final Logger log = LoggerFactory.getLogger(AsyncEmailSender.class);
	private final JavaMailSender mailSender;

	public AsyncEmailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Async
	@Override
	public void send(String to, String subject, String htmlBody) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(htmlBody, true); // HTML
			mailSender.send(message);
			log.info("메일 전송 완료: to={}, subject={}", to, subject);
		} catch (Exception e) {
			log.error("메일 전송 실패(to={}): {}", to, e.getMessage(), e);
		}
	}
}
