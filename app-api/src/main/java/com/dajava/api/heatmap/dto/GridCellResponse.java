package com.dajava.api.heatmap.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "히트맵 그리드 셀 응답")
public record GridCellResponse(
	@Schema(description = "그리드 X 좌표", example = "5")
	int gridX,

	@Schema(description = "그리드 Y 좌표", example = "10")
	int gridY,

	@Schema(description = "이벤트 발생 횟수", example = "15")
	int count,

	@Schema(description = "최대 카운트 대비 강도 (0-100)", example = "75")
	int intensity
) {
	public static GridCellResponse from(com.dajava.domain.heatmap.GridCell cell) {
		return new GridCellResponse(cell.gridX(), cell.gridY(), cell.count(), cell.intensity());
	}
}
