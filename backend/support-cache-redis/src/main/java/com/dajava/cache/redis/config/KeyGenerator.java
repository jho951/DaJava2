package com.dajava.cache.redis.config;

import com.dajava.api.domain.log.dto.ClickEventRequest;
import com.dajava.api.domain.log.dto.MovementEventRequest;
import com.dajava.api.domain.log.dto.ScrollEventRequest;
import com.dajava.api.domain.log.dto.identifier.SessionIdentifier;
import com.dajava.api.global.utils.LogUtils;

public class KeyGenerator {
	private static final String EVENT_CACHE_PREFIX = "event:";
	private static final String LAST_UPDATED_PREFIX = "lastUpdated:";

	public static <T> String buildEventKey( SessionIdentifier sessionIdentifier,T event) {
		String eventType;
		if (event instanceof ClickEventRequest) {
			eventType = "click";
		} else if (event instanceof MovementEventRequest) {
			eventType = "move";
		} else if (event instanceof ScrollEventRequest) {
			eventType = "scroll";
		} else {
			throw new IllegalArgumentException("Unknown event type: " + event.getClass().getSimpleName());
		}
		return EVENT_CACHE_PREFIX + LogUtils.createRedisKey(sessionIdentifier) + ":" + eventType;
	}


	public static String buildLastUpdatedKey(String eventKey) {
		return LAST_UPDATED_PREFIX + eventKey;
	}
}
