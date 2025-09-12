package com.dajava.application.event.validater;

import org.springframework.stereotype.Component;

import com.dajava.api.domain.event.dto.PointerMoveEventRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PointerMoveEventValidator implements EventValidator<PointerMoveEventRequest> {

	@Override
	public void validate(PointerMoveEventRequest request) {
		EventValidation.validateNonZeroFields(
			request.getBrowserWidth(), request.getViewportHeight(), request.getScrollHeight()
		);
		EventValidation.validateCoordinateRange(
			request.getClientX(), request.getClientY(), request.getBrowserWidth(), request.getViewportHeight()
		);
		EventValidation.validateTimestamp(request.getTimestamp());
	}
}
