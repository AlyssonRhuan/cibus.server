package com.arcs.cibus.server.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReportType {

	SIMPLE_DATA,
	LINE,
	PIE,
	COLUMN;
}
