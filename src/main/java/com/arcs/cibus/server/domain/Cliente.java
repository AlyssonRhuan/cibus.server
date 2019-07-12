package com.arcs.cibus.server.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.arcs.cibus.server.domain.enums.TipoCliente;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.ClienteSerializer;
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
@JsonSerialize(using = ClienteSerializer.class)
@Entity(name = "cibus_clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;    
	@Transient
	@Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.COMPLETA;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long clienteID;
	private String nome;
	private String email;
	private String cpfCnpj;
	
	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;

    @Builder.Default
	@OneToMany(mappedBy = "cliente")
	private List<Endereco> enderecos = new ArrayList<>();

    @Builder.Default
	@ElementCollection
	@CollectionTable(name = "cibus_telefone")
	private Set<String> telefones = new HashSet<>();
	
	public void addEndereco(Endereco endereco) {
		this.enderecos.add(endereco);
	}
	
	public void addEnderecos(List<Endereco> enderecos) {
		this.enderecos.addAll(enderecos);
	}	
	
	public void addTelefone(String telefone) {
		this.telefones.add(telefone);
	}
	
	public void addTelefones(List<String> telefones) {
		this.telefones.addAll(telefones);
	}	

	public void setTipoSerializer(TipoSerializer tipoSerializer) {
		this.tipoSerializer = tipoSerializer;
	}	
}
