package com.arcs.cibus.server.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.ProdutoSerializer;
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
@JsonSerialize(using = ProdutoSerializer.class)
@Entity(name = "cibus_produtos")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;    
	@Transient
	@Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.COMPLETA;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long produtoID;
	private String nome;
	private BigDecimal preco;
	private Double estoqueMinimo;
	private String imagem;
	private Double quantidadeEstoque;
	private Boolean visivel;

	@Builder.Default
	@ManyToMany
	@JoinTable(name = "cibus_produto_categoria", 
		joinColumns = @JoinColumn(name = "produtoId"), 
		inverseJoinColumns = @JoinColumn(name = "categoriaId"))
	private List<Categoria> categorias = new ArrayList<>();
	
	@Builder.Default
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens = new ArrayList<>();
	
	public List<Pedido> pedidos(){
		List<Pedido> pedidos = new ArrayList<>();
		for(ItemPedido itemPedido : itens) {
			pedidos.add(itemPedido.getPedido());			
		}
		return pedidos;
	}

	public void addCategoria(Categoria categoria) {
		categorias.add(categoria);
	}

	public void addCategorias(List<Categoria> categorias) {
		this.categorias.addAll(categorias);
	}
	
	public void addItemPedido(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public void setTipoSerializer(TipoSerializer tipoSerializer) {
		this.tipoSerializer = tipoSerializer;
	}	
}
