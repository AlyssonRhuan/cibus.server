package com.arcs.cibus.server.domain.enums;

import lombok.Getter;

@Getter
public enum TipoCliente {
	
	PESSOA_FISICA("Pessoa Física"),
	PESSOA_JURIDICA("Pessoa Jurídica");
	
	private final String label;
	
	private TipoCliente(String label) {
		this.label = label;
	}	
	
	public static TipoCliente toEnum(final String value) {
		if(value == null || value.trim().equals("")) return null;
		
		for(TipoCliente tipo : TipoCliente.values()) {
			if(tipo.toString().equals(value)) 
				return tipo;
		}
		
		throw new IllegalArgumentException("Valor " + value + " inválido.");
	}	
}
