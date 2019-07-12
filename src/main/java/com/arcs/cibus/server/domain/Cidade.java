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
import com.arcs.cibus.server.serializer.CidadeSerializer;
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonSerialize(using = CidadeSerializer.class)
@Entity(name = "cibus_cidades")
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;   
	@Transient 
	@Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.COMPLETA;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long cidadeID;
	private String nome;	

	@ManyToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;

	public void setTipoSerializer(TipoSerializer tipoSerializer) {
		this.tipoSerializer = tipoSerializer;
	}	
}
