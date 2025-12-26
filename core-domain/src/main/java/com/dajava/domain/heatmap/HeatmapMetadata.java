package com.dajava.domain.heatmap;

import java.time.LocalDateTime;

public record HeatmapMetadata(
	int maxCount,
	int totalEvents,
	String pageUrl,
	int totalSessions,
	LocalDateTime firstEventTime,
	LocalDateTime lastEventTime
) {
	public HeatmapMetadata {
		if (maxCount < 0 || totalEvents < 0 || totalSessions < 0) {
			throw new IllegalArgumentException("카운트 값은 음수일 수 없습니다.");
		}
	}
}
