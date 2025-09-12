package com.dajava.api.event.config.properties.analyzer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "analyzer.scroll")
@Data
@Component
public class ScrollAnalyzerProperties {
	private long timeWindowMs;
	private int minScrollDelta;
	private int minEventCount;
	private int rageThresholdPerWindow;
	private int minDirectionChanges;
	private double contentConsumedThreshold;
	private int scrollBottomThreshold;
}