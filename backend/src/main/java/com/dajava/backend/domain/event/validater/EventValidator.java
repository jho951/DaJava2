package com.dajava.api.domain.event.validater;

public interface EventValidator<T> {
	void validate(T request);
}