	package com.arcs.cibus.server.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.arcs.cibus.server.domain.enums.EstadoPagamento;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.PagamentoCartaoSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = PagamentoCartaoSerializer.class)
@Entity(name = "cibus_pagamento_cartao")
public class PagamentoCartao extends Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;    
	@Transient
	@Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.COMPLETA;
	
	private Integer numeroParcelas;

	@Builder
	public PagamentoCartao(Long pagamentoID, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
		super(pagamentoID, estado, pedido);
		this.numeroParcelas = numeroParcelas;
	}
}
	