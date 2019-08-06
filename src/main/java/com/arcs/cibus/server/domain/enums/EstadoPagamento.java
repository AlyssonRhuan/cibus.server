package com.arcs.cibus.server.domain.enums;

import lombok.Getter;

@Getter
public enum EstadoPagamento {

	PENDENTE("Pendente"),
	QUITADO("Quitado"),
	CANCELADO("Cancelado");
	
	private final String label;
	
	private EstadoPagamento(String label) {
		this.label = label;
	}	
	
	public static EstadoPagamento toEnum(final String value) {
		if(value == null || value.trim().equals("")) return null;
		
		for(EstadoPagamento tipo : EstadoPagamento.values()) {
			if(tipo.toString().equals(value)) 
				return tipo;
		}
		
		throw new IllegalArgumentException("Valor " + value + " inválido.");
	}	
}
