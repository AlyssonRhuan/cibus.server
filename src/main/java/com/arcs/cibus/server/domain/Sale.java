package com.arcs.cibus.server.domain;

import com.arcs.cibus.server.domain.enums.SaleStatus;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.CategorySerializer;
import com.arcs.cibus.server.serializer.SaleSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonSerialize(using = SaleSerializer.class)
@Table
@Entity(name = "cibus_sales")
public class Sale implements Serializable {

	private static final long serialVersionUID = 1L;    
	@Transient
	@Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.FULL;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private Long quantity;
	private BigDecimal price;
	private Date saleDate;

	@Enumerated(EnumType.STRING)
	private SaleStatus saleStatus;

	@OneToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;

	@OneToOne
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	private User client;
}
	