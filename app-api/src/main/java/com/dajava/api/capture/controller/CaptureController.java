package com.dajava.api.capture.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.dajava.api.capture.dto.PageCaptureRequest;
import com.dajava.api.capture.dto.PageCaptureResponse;
import com.dajava.application.capture.PageCaptureUseCase;
import com.dajava.application.capture.PageCaptureCommand;

@RestController
@RequestMapping("/api/capture")
public class CaptureController {

	private final PageCaptureUseCase useCase;

	public CaptureController(PageCaptureUseCase useCase) {
		this.useCase = useCase;
	}

	@PostMapping(value = "/page-capture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public PageCaptureResponse pageCapture(@ModelAttribute PageCaptureRequest req) {
		var command = new PageCaptureCommand(req.pageUrl(), req.serialNumber());
		var result  = useCase.createPageCapture(command);
		return PageCaptureResponse.from(result);
	}
}
