package com.dajava.api.register.controller;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 개발/테스트용 (dev 프로필에서만)
@Profile("dev")
@RestController
@RequestMapping(value = "/v1/dev/registers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Register (Dev)")
@RequiredArgsConstructor
class RegisterDevController {
	private final RegisterCacheService cache;
	private final RegisterService registerService;

	@GetMapping("/cache")
	public Set<String> cacheList() { return cache.getSerialNumberCache(); }

	@PostMapping("/{serial}/expire")
	public void expire(@PathVariable String serial) { registerService.expireRegister(serial); }
}
