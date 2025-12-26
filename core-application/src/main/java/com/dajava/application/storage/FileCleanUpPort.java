package com.dajava.application.storage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.dajava.api.domain.register.entity.PageCaptureData;
import com.dajava.api.domain.register.entity.Register;
import com.dajava.api.domain.register.repository.PageCaptureDataRepository;
import com.dajava.api.domain.register.repository.RegisterRepository;

@Component
public class FileCleanUpPort {

	private final RegisterRepository registerRepository;
	private final PageCaptureDataRepository pageCaptureDataRepository;

	public FileCleanUpPort(RegisterRepository registerRepository,
		PageCaptureDataRepository pageCaptureDataRepository) {
		this.registerRepository = registerRepository;
		this.pageCaptureDataRepository = pageCaptureDataRepository;
	}

	public Set<String> getRegisterUrls() {
		List<Register> registers = registerRepository.findAll();

		return registers.stream()
			.map(Register::getUrl)
			.collect(Collectors.toSet());
	}

	public Set<String> getPageCaptureUrls() {
		List<PageCaptureData> pageCaptureData = pageCaptureDataRepository.findAll();

		return pageCaptureData.stream()
			.map(PageCaptureData::getPageUrl)
			.collect(Collectors.toSet());
	}
}
