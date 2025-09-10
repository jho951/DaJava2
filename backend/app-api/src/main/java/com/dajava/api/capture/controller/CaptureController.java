package com.dajava.api.capture.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class CaptureController {
	@PostMapping(value = "/page-capture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public PageCaptureResponse pageCapture(@ModelAttribute PageCaptureRequest req) {
		return registerService.createPageCapture(req);
	}
}
