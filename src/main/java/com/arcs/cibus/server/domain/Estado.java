package com.arcs.cibus.server.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.EstadoSerializer;
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
@JsonSerialize(using = EstadoSerializer.class)
@Entity(name = "cibus_estados")
public class Estado implements Serializable {
	
	private static final long serialVersionUID = 1L;  
	@Transient  
	@Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.COMPLETA;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long estadoId;
	private String nome;

	@Builder.Default
	@OneToMany(mappedBy = "estado")
	private List<Cidade> cidades = new ArrayList<>();

	public void addCidade(Cidade cidade) {
		cidades.add(cidade);
	}

	public void addCidades(List<Cidade> cidades) {
		this.cidades.addAll(cidades);
	}

	public void setTipoSerializer(TipoSerializer tipoSerializer) {
		this.tipoSerializer = tipoSerializer;
	}	
}
