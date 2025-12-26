package com.dajava.api.error;

import org.springframework.http.HttpStatus;

public interface ErrorResolver {
	HttpStatus resolveStatus(String code);
	String resolveMessage(String code, String fallback);
}
