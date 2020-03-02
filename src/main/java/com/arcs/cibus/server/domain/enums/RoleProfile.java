package com.arcs.cibus.server.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleProfile {

	ADMIN(1, "ROLE_ADMIN"),
	SALESMAN(2, "ROLE_SALESMAN"),
	CLIENT(3, "ROLE_CLIENT");	
	
	private int code;
	private String description;
	
	private static RoleProfile toEnum (Integer code) {
		if (code == null) {
			return null;
		}
		
		for (RoleProfile role : RoleProfile.values()){			
			if(role.getCode() == code)
				return role;
		}		
		
		return null;
	}
	
}
