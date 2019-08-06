	package com.arcs.cibus.server.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.arcs.cibus.server.domain.enums.EstadoPagamento;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.PagamentoBoletoSerializer;
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
@JsonSerialize(using = PagamentoBoletoSerializer.class)
@Entity(name = "cibus_pagamento_boleto")
public class PagamentoBoleto extends Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;    
	@Transient
	@Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.COMPLETA;
	
	private Date dataVencimento;
	private Date dataPagamento;
	
	@Builder
	public PagamentoBoleto(Long pagamentoID, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(pagamentoID, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}
	
	
	
}
	