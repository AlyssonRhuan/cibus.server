package com.arcs.cibus.server.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Profile {

	ADMIN(1, "ROLE_ADMIN"),
	SALESMAN(2, "ROLE_SALESMAN"),
	CLIENT(3, "ROLE_CLIENT");	
	
	private int code;
	private String description;
	
	public static Profile toEnum (Integer code) {
		if (code == null) {
			return null;
		}
		
		for (Profile role : Profile.values()){			
			if(role.getCode() == code)
				return role;
		}		
		
		return null;
	}
	
}