package com.arcs.cibus.server.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.PerfilSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonSerialize(using = PerfilSerializer.class)
@Entity(name = "cibus_perfis")
public class Perfil implements Serializable {

	private static final long serialVersionUID = 1L;    
	@Transient
	@Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.COMPLETA;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long perfilID;
	private String nome;

	@Builder.Default
	@OneToMany
	@JoinTable(name = "cibus_perfil_tela", 
		joinColumns = @JoinColumn(name = "perfilId"), 
		inverseJoinColumns = @JoinColumn(name = "telaId"))
	private List<Tela> telas = new ArrayList<>();

	public void addTela(Tela tela) {
		telas.add(tela);
	}

	public void addTelas(List<Tela> telas) {
		this.telas.addAll(telas);
	}
}