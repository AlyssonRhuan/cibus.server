package com.arcs.cibus.server.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.PedidoSerializer;
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
@JsonSerialize(using = PedidoSerializer.class)
@Entity(name = "cibus_pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;    
	@Transient
	@Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.COMPLETA;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long pedidoID;
	private Date instante;
	
	@ManyToOne
	@JoinColumn(name =  "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "endereco_entraga_id")
	private Endereco enderecoEntrega;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;
	
	@Builder.Default
	@OneToMany(mappedBy = "pedido")
	private Set<ItemPedido> itens = new HashSet<>();

	public void setTipoSerializer(TipoSerializer tipoSerializer) {
		this.tipoSerializer = tipoSerializer;
	}

	/**
	 * @param pagamento the pagamento to set
	 */
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}	
}
	