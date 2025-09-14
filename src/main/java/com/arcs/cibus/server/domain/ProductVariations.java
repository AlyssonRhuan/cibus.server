package com.arcs.cibus.server.domain;

import com.arcs.cibus.server.domain.enums.ProductVariationType;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.ProductSerializer;
import com.arcs.cibus.server.serializer.ProductVariationsSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonSerialize(using = ProductVariationsSerializer.class)
@Entity(name = "cibus_products_variations")
public class ProductVariations implements Serializable {

	private static final long serialVersionUID = 1L;    
	@Transient
	@Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.FULL;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Enumerated(EnumType.STRING)
	private ProductVariationType productVariationType;
	private String variation;
	private BigDecimal price;

	@ManyToOne
	@JoinColumn (name = "product_id")
	private Product product;
}
