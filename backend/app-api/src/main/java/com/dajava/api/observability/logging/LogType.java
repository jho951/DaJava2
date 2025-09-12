package com.dajava.api.observability.logging;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LogType {
	API("api"),
	SYSTEM("system"),
	DEFAULT("default");

	private final String type;
}
