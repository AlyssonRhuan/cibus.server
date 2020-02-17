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
import javax.persistence.Transient;

import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.ProductSerializer;
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
@JsonSerialize(using = ProductSerializer.class)
@Entity(name = "cibus_products")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;    
	@Transient
	@Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.FULL;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private String name;
	private BigDecimal price;
	private Double minimumStock;
	private String image;
	private Double stockQuantity;
	private Boolean visible;

	@Builder.Default
	@ManyToMany
	@JoinTable(name = "cibus_product_ccategory", 
		joinColumns = @JoinColumn(name = "productId"), 
		inverseJoinColumns = @JoinColumn(name = "categoryId"))
	private List<Category> categorys = new ArrayList<>();

	public void addCategory(Category category) {
		categorys.add(category);
	}

	public void addCategorys(List<Category> categorys) {
		this.categorys.addAll(categorys);
	}	
}