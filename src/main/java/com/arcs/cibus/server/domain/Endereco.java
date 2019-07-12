package com.arcs.cibus.server.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.EnderecoSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonSerialize(using = EnderecoSerializer.class)
@Entity(name = "cibus_endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;  
	@Transient  
	@Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.COMPLETA;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long enderecoID;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;

	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public void setTipoSerializer(TipoSerializer tipoSerializer) {
		this.tipoSerializer = tipoSerializer;
	}	
}
