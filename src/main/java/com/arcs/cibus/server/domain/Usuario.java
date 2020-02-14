package com.arcs.cibus.server.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.UsuarioSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@JsonSerialize(using = UsuarioSerializer.class)
@Entity(name = "cibus_usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;    
	@Transient
	@Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.COMPLETA;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private String nome;
	
	@OneToOne
	private UsuarioAcoes acoes;	
}
