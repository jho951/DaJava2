package com.dajava.api.domain.event.validater;

import org.springframework.stereotype.Component;

import com.dajava.api.domain.event.dto.PointerScrollEventRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PointerScrollEventValidator implements EventValidator<PointerScrollEventRequest> {

	@Override
	public void validate(PointerScrollEventRequest request) {
		EventValidation.validateNonZeroFields(
			request.getBrowserWidth(), request.getViewportHeight(), request.getScrollHeight()
		);
		EventValidation.validateTimestamp(request.getTimestamp());
	}
}