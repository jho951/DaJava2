package com.dajava.api.domain.solution.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dajava.api.domain.event.entity.SolutionData;
import com.dajava.api.domain.event.repository.SolutionDataRepository;
import com.dajava.api.domain.register.entity.Register;
import com.dajava.api.domain.register.repository.RegisterRepository;
import com.dajava.api.domain.solution.dto.SolutionInfoResponse;
import com.dajava.api.domain.solution.entity.Solution;
import com.dajava.api.domain.solution.exception.SolutionException;
import com.dajava.api.domain.solution.repository.SolutionRepository;
import com.dajava.api.global.exception.ErrorCode;
import com.dajava.api.global.utils.PasswordUtils;

class SolutionServiceImplTest {

	@Mock
	private SolutionDataRepository solutionDataRepository;

	@Mock
	private RegisterRepository registerRepository;

	@Mock
	private SolutionRepository solutionRepository;

	@InjectMocks
	private SolutionServiceImpl solutionService;

	private SolutionData solutionData;
	private Register register;
	private Solution solution;

	private String serialNumber;
	private String correctPassword;
	private String inCorrectPassword;

	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.openMocks(this);    //NPE 방지(mock객체 필드 초기화)

		serialNumber = "11db0706-4879-463a-a4d7-f7c347668cc6";
		correctPassword = "correctPassword";
		inCorrectPassword = "inCorrectPassword";
		solutionData = SolutionData.create(serialNumber);
		register = Register.builder()
			.serialNumber(serialNumber)
			.email("test@example.com")
			.password(PasswordUtils.hashPassword(correctPassword))
			.url("http://example.com")
			.startDate(LocalDateTime.now())
			.endDate(LocalDateTime.now().plusMonths(1))
			.duration(30)
			.isServiceExpired(false)
			.isSolutionComplete(false)
			.build();
		solution = Solution.builder()
			.text("test")
			.build();
	}

	@Test
	void getAISolution() {
	}

	@Test
	void getSolutionInfo_correctSerialNumberAndPassword() {
		//given
		when(registerRepository.findBySerialNumber(serialNumber)).thenReturn(Optional.ofNullable(register));
		when(solutionRepository.findByRegister(register)).thenReturn(Optional.of(solution));

		// when
		SolutionInfoResponse result = solutionService.getSolutionInfo(serialNumber, correctPassword);

		//then
		assertNotNull(result);
		assertEquals(result.text(), solution.getText());
		verify(registerRepository, times(1)).findBySerialNumber(serialNumber);
		verify(solutionRepository, times(1)).findByRegister(register);
	}

	@Test
	void getSolutionInfo_correctSerialNumberAndinCorrectPassword() {
		//given
		when(registerRepository.findBySerialNumber(serialNumber)).thenReturn(Optional.ofNullable(register));
		when(solutionRepository.findByRegister(register)).thenReturn(Optional.of(solution));

		// when
		SolutionException exception = assertThrows(SolutionException.class,
			() -> solutionService.getSolutionInfo(serialNumber, inCorrectPassword));

		//then
		assertEquals(ErrorCode.SOLUTION_PASSWORD_INVALID, exception.errorCode);
	}

	@Test
	void getSolutionData_success() {
		//given
		when(solutionDataRepository.findBySerialNumber(serialNumber)).thenReturn(Optional.ofNullable(solutionData));

		// when
		SolutionData result = solutionService.getSolutionData(serialNumber);

		// then
		assertNotNull(result);
		assertEquals(serialNumber, result.getSerialNumber());
		verify(solutionDataRepository, times(1)).findBySerialNumber(serialNumber);
	}

	@Test
	void getSolutionData_fail() {
		//given
		String wrongSerialNumber = "aaaa";
		when(solutionDataRepository.findBySerialNumber(wrongSerialNumber)).thenReturn(null);

		// when & then
		assertThrows(Exception.class,
			() -> solutionService.getSolutionData(wrongSerialNumber));

		verify(solutionDataRepository, times(1)).findBySerialNumber(wrongSerialNumber);
	}
}
