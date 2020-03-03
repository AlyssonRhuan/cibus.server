package com.arcs.cibus.server.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ViewContext {

	GENERAL("General"),
	ADMINISTRATIVE("Administrative"),
	PERSONAL("Personal");	
	
	private String description;
	
	public static ViewContext toEnum (Integer code) {
		if (code == null) {
			return null;
		}
		
		return null;
	}
	
}
