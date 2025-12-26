package com.dajava.application.capture;

public record PageCaptureResult(
	boolean success,
	String message,
	String captureFileName
) {}