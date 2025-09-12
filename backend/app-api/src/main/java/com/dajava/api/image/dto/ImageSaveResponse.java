package com.dajava.api.image.dto;

import lombok.Builder;

@Builder
public record ImageSaveResponse(
	int widthRange,
	String fileName
) {
}
