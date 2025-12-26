package com.dajava.application.event.validater;

public interface EventValidator<T> {
	void validate(T request);
}