package com.dajava.cache.redis.adapter;

import com.dajava.domain.heatmap.*;
import com.dajava.domain.heatmap.port.HeatmapCachePort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
public class RedisHeatmapCacheAdapter implements HeatmapCachePort {

	private final ValueOperations<String, Object> ops;

	public RedisHeatmapCacheAdapter(RedisTemplate<String, Object> redisTemplate) {
		this.ops = redisTemplate.opsForValue();
	}

	private String key(TileKey k) {
		// 키 설계: page는 공백/쿼리 제거 등 정규화 고려
		return "heatmap:%s:%d:%d:%d".formatted(k.page(), k.zoom(), k.x(), k.y());
	}

	@Override
	public Optional<HeatmapTile> get(TileKey k) {
		Object v = ops.get(key(k));
		return Optional.ofNullable((HeatmapTile) v);
	}

	@Override
	public void put(TileKey k, HeatmapTile value, Duration ttl) {
		ops.set(key(k), value, ttl);
	}

	@Override
	public void evict(TileKey k) {
		ops.getOperations().delete(key(k));
	}
}
