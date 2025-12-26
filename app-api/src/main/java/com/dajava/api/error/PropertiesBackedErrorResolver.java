package com.dajava.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class PropertiesBackedErrorResolver implements ErrorResolver {

	private final ErrorProperties props;

	public PropertiesBackedErrorResolver(ErrorProperties props) {
		this.props = props;
	}

	@Override
	public HttpStatus resolveStatus(String code) {
		return props.resolveStatus(code);
	}

	@Override
	public String resolveMessage(String code, String fallback) {
		String msg = props.resolveMessage(code);
		return (msg != null && !msg.isBlank()) ? msg : fallback;
	}
}
