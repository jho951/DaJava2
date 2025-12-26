package com.dajava.cache.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis 기본 설정을 담당하는 구성 클래스입니다.
 * <p>
 * - Spring Boot가 제공하는 {@link RedisConnectionFactory} 자동설정을 그대로 사용합니다
 *   (host/port/password 등은 application.yml의 spring.data.redis.* 로 제어).
 * - {@link RedisTemplate}의 Key/Value/Hash 직렬화를 명시적으로 설정해
 *   사람이 읽기 쉬운 JSON 포맷으로 저장·조회가 가능하도록 합니다.
 * - {@link JavaTimeModule}을 등록하여 LocalDateTime 등 Java Time API 타입의
 *   직렬화/역직렬화를 안전하게 지원합니다.
 * </p>
 *
 * <p>이 설정은 캐시 어댑터(예: RedisHeatmapCacheAdapter)에서 주입 받아
 * 도메인 객체를 그대로 Redis에 저장/조회하는 데 사용됩니다.</p>
 */
@Configuration
public class RedisConfig {

	/**
	 * 애플리케이션 전역에서 사용할 {@link RedisTemplate} Bean을 등록합니다.
	 * <p>
	 * - Key/HashKey: {@link StringRedisSerializer} (문자열)
	 * - Value/HashValue: {@link GenericJackson2JsonRedisSerializer} (JSON)
	 * </p>
	 *
	 * @param cf Spring Boot 자동설정이 제공하는 Redis 커넥션 팩토리
	 * @return JSON 직렬화를 사용하는 {@link RedisTemplate} (key=String, value=Object)
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory cf) {
		RedisTemplate<String, Object> tpl = new RedisTemplate<>();
		tpl.setConnectionFactory(cf);

		ObjectMapper om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());

		GenericJackson2JsonRedisSerializer json = new GenericJackson2JsonRedisSerializer(om);

		tpl.setKeySerializer(new StringRedisSerializer());
		tpl.setValueSerializer(json);
		tpl.setHashKeySerializer(new StringRedisSerializer());
		tpl.setHashValueSerializer(json);

		tpl.afterPropertiesSet();
		return tpl;
	}
}
