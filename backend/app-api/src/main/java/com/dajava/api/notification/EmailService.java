package com.dajava.api.notification;

import com.dajava.domain.notification.port.EmailSenderPort;
import org.springframework.stereotype.Service;

/**
 * 애플리케이션 서비스 계층:
 * - 도메인/비즈니스 시나리오별 제목/본문을 조립하고
 * - 포트(EmailSenderPort)를 통해 실제 전송을 위임한다.
 */
@Service
public class EmailService {

	private final EmailSenderPort sender;

	public EmailService(EmailSenderPort sender) {
		this.sender = sender;
	}

	public void sendSignupComplete(String to, String nickname) {
		String subject = "[DaJava] 회원가입이 완료되었습니다";
		String body = """
            <h3>%s 님, 환영합니다!</h3>
            <p>DaJava 가입이 완료되었습니다. 즐거운 사용 되세요.</p>
            """.formatted(nickname);
		sender.send(to, subject, body);
	}

	public void sendJobFinished(String to, String jobName) {
		String subject = "[DaJava] 작업 완료 알림: " + jobName;
		String body = """
            <p>요청하신 작업(<b>%s</b>)이 정상적으로 완료되었습니다.</p>
            """.formatted(jobName);
		sender.send(to, subject, body);
	}
}
