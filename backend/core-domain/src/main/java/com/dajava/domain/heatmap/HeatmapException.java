package com.dajava.domain.heatmap;

import com.dajava.api.global.exception.ErrorCode;

public class HeatmapException extends RuntimeException {
	public final ErrorCode errorCode;

	public HeatmapException(final ErrorCode errorCode) {
		super(errorCode.getDescription());
		this.errorCode = errorCode;
	}
}
