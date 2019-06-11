package com.arcs.cibus.server.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
@Entity(name = "cibus_produtos")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long produtoID;
	private String nome;
	private BigDecimal preco;
	private Double estoqueMinimo;
	private String imagem;
	private Double quantidadeEstoque;
	private Boolean visivel;
//    private Pessoa Fornecedor;//
//    private Categoria Categoria;//
//    private Estoque Estoque;

	public Produto() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produtoID == null) ? 0 : produtoID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (produtoID == null) {
			if (other.produtoID != null)
				return false;
		} else if (!produtoID.equals(other.produtoID))
			return false;
		return true;
	}
}
