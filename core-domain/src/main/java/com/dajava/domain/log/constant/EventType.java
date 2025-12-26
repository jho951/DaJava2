package com.dajava.domain.log.constant;

import lombok.Getter;

@Getter
public enum EventType {
	CLICK("click:"),
	MOVE("move:"),
	SCROLL("scroll:");

	private final String prefix;

	EventType(String prefix) {
		this.prefix = prefix;
	}
}
