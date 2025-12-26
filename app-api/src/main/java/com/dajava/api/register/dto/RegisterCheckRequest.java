package com.dajava.api.register.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
/**
 * <p>{@code SolutiionCheckRequest}는 시리얼 넘버와 표현하는 DTO입니다.</p>
 */
public record RegisterCheckRequest(
	@Schema(description = "등록 시리얼 번호", example = "5_team_testSerial",requiredMode = Schema.RequiredMode.REQUIRED)
	@NotBlank(message = "시리얼 번호는 필수입니다.")
	String serialNumber,

	@Schema(description = "등록 비밀번호", example = "test1234!",requiredMode = Schema.RequiredMode.REQUIRED)
	@NotNull(message = "비밀번호는 필수입니다.")
	String password
) {

}