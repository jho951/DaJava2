package com.dajava.api.register.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dajava.api.register.dto.RegisterCheckRequest;
import com.dajava.api.register.dto.RegisterCheckResponse;
import com.dajava.api.register.dto.RegisterCreateRequest;
import com.dajava.api.register.dto.RegisterCreateResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/v1/registers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Register (Public)")
@RequiredArgsConstructor
class RegisterPublicController {
	private final RegisterService registerService;

	@PostMapping
	public RegisterCreateResponse create(@Valid @RequestBody RegisterCreateRequest req) {
		return registerService.createRegister(req);
	}

	@PostMapping("/check")
	public RegisterCheckResponse check(@Valid @RequestBody RegisterCheckRequest req) {
		return registerService.getSolutionCheck(req);
	}

}


