package com.dajava.api.register.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dajava.api.register.dto.RegisterModifyRequest;
import com.dajava.api.register.dto.RegisterModifyResponse;
import com.dajava.api.register.dto.RegistersInfoRequest;
import com.dajava.api.register.dto.RegistersInfoResponse;

import jakarta.servlet.http.HttpServletRequest;

// 관리자용
@RestController
@RequestMapping(value = "/v1/admin", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Register (Admin)")
@RequiredArgsConstructor
class RegisterAdminController {
	private final RegisterService registerService;
	private final AdminService adminService;

	@PostMapping("/login")
	public void login(@RequestParam String adminCode, HttpServletResponse res) {
		adminService.login(adminCode, res);
	}

	@GetMapping("/registers")
	public RegistersInfoResponse list(@ModelAttribute RegistersInfoRequest req, HttpServletRequest http) {
		adminService.authorize(http);
		return registerService.getRegisterList(req);
	}

	@PatchMapping("/registers/{id}")
	public RegisterModifyResponse modify(@PathVariable Long id, @RequestBody RegisterModifyRequest req, HttpServletRequest http) {
		adminService.authorize(http);
		return registerService.modifySolution(req, id);
	}

	@DeleteMapping("/registers/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id, HttpServletRequest http) {
		adminService.authorize(http);
		registerService.deleteSolution(id);
	}
}