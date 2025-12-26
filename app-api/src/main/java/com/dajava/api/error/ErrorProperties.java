package com.dajava.api.error;

import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
@ConfigurationProperties(prefix = "errors")
public class ErrorProperties {

	private Map<String, Integer> status;
	private Map<String, String> messages;

	public Map<String, Integer> getStatus() { return status; }
	public void setStatus(Map<String, Integer> status) { this.status = status; }

	public Map<String, String> getMessages() { return messages; }
	public void setMessages(Map<String, String> messages) { this.messages = messages; }

	public HttpStatus resolveStatus(String code) {
		return HttpStatus.valueOf(status.getOrDefault(code, 500));
	}

	public String resolveMessage(String code) {
		return messages.getOrDefault(code, "Unexpected error");
	}
}
