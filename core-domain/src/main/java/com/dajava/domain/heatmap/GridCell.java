package com.dajava.domain.heatmap;

public record GridCell(
	int gridX,
	int gridY,
	int count,
	int intensity
) {
	public GridCell {
		if (gridX < 0 || gridY < 0) {
			throw new IllegalArgumentException("Grid 좌표는 음수일 수 없습니다.");
		}
		if (intensity < 0 || intensity > 100) {
			throw new IllegalArgumentException("intensity는 0~100 사이여야 합니다.");
		}
	}
}
