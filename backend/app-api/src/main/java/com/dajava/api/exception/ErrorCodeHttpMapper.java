package com.dajava.api.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.dajava.domain.common.DomainErrorCode;

public final class ErrorCodeHttpMapper {

	private ErrorCodeHttpMapper() {}

	private static final Map<DomainErrorCode, HttpStatus> MAP = Map.ofEntries(
		// Register
		Map.entry(DomainErrorCode.INVALID_REGISTER_REQUEST, HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.ALREADY_REGISTER_URL,   HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.REGISTER_URL_EMPTY,     HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.MODIFY_DATE_EXCEEDED,   HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.INVALID_PAGE_CAPTURE,   HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.REGISTER_NOT_FOUND,     HttpStatus.NOT_FOUND),
		Map.entry(DomainErrorCode.MOBILE_VIEW_NOT_SUPPORTED, HttpStatus.BAD_REQUEST),

		// Solution
		Map.entry(DomainErrorCode.SOLUTION_SERIAL_NUMBER_NOT_FOUND, HttpStatus.NOT_FOUND),
		Map.entry(DomainErrorCode.SOLUTION_DATA_NOT_FOUND,          HttpStatus.NOT_FOUND),
		Map.entry(DomainErrorCode.SOLUTION_EVENT_DATA_NOT_FOUND,    HttpStatus.NOT_FOUND),
		Map.entry(DomainErrorCode.SOLUTION_NOT_FOUND,               HttpStatus.NOT_FOUND),
		Map.entry(DomainErrorCode.SOLUTION_SERIAL_NUMBER_INVALID,   HttpStatus.UNAUTHORIZED),
		Map.entry(DomainErrorCode.SOLUTION_PASSWORD_INVALID,        HttpStatus.UNAUTHORIZED),
		Map.entry(DomainErrorCode.SOLUTION_TEXT_EMPTY,              HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.SOLUTION_PARSING_ERROR,           HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.SOLUTION_RESPONSE_ERROR,          HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.SOLUTION_EXPIRED_ERROR,           HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.INVALID_EVENT_TYPE,               HttpStatus.BAD_REQUEST),

		// Admin
		Map.entry(DomainErrorCode.INVALID_ADMIN_CODE, HttpStatus.UNAUTHORIZED),
		Map.entry(DomainErrorCode.AUTHORIZE_ERROR,    HttpStatus.FORBIDDEN),

		// Event
		Map.entry(DomainErrorCode.ALREADY_ENDED_SESSION,            HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.ALREADY_VERIFIED_SESSION,         HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.EVENT_DOCUMENT_NOT_FOUND,         HttpStatus.NOT_FOUND),
		Map.entry(DomainErrorCode.SESSION_DATA_DOCUMENT_NOT_FOUND,  HttpStatus.NOT_FOUND),
		Map.entry(DomainErrorCode.ALREADY_OUTLIER_DOCUMENT,         HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.EVENT_DTO_NOT_FOUND,              HttpStatus.NOT_FOUND),
		Map.entry(DomainErrorCode.INVALID_BROWSER_WIDTH,            HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.INVALID_VIEWPORT_HEIGHT,          HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.INVALID_SCROLL_HEIGHT,            HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.INVALID_CLIENT_X,                 HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.INVALID_CLIENT_Y,                 HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.INVALID_TIMESTAMP,                HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.INVALID_ELEMENT_HTML,             HttpStatus.BAD_REQUEST),

		// Response
		Map.entry(DomainErrorCode.DATA_TO_STRING_ERROR,             HttpStatus.VARIANT_ALSO_NEGOTIATES),

		// Log
		Map.entry(DomainErrorCode.SESSION_IDENTIFIER_NOT_FOUND,         HttpStatus.NOT_FOUND),
		Map.entry(DomainErrorCode.SESSION_IDENTIFIER_PARSING_NOT_FOUND, HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.SESSION_IDENTIFIER_PARSING_ERROR,     HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.REDIS_CACHING_ERROR,                  HttpStatus.BAD_REQUEST),

		// Image
		Map.entry(DomainErrorCode.INVALID_IMAGE_FILE,               HttpStatus.BAD_REQUEST),
		Map.entry(DomainErrorCode.IMAGE_IO_ERROR,                   HttpStatus.INTERNAL_SERVER_ERROR),

		// heatmap
		Map.entry(DomainErrorCode.ELASTICSEARCH_QUERY_FAILED,       HttpStatus.BAD_REQUEST),

		// htmlparser
		Map.entry(DomainErrorCode.PARSING_ERROR,                    HttpStatus.BAD_REQUEST),

		// abusingBaseLine
		Map.entry(DomainErrorCode.ABUSING_BASE_LINE_NOT_FOUND,      HttpStatus.NOT_FOUND),
		Map.entry(DomainErrorCode.ALREADY_PRIOR_AVERAGE,            HttpStatus.BAD_REQUEST)
	);

	public static HttpStatus resolve(DomainErrorCode code) {
		return MAP.getOrDefault(code, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
