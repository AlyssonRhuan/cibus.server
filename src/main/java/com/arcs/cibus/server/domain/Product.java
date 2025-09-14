package com.arcs.cibus.server.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

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
	
	@Column(columnDefinition="TEXT")
	private String description;
	private Boolean visible;

	@Transient
	private String image;

	private String imagePath;
	private BigDecimal price;
	private int stockQuantity;
	private int minimumStock;

	private Boolean isActive;

	@Builder.Default
	@OneToMany (mappedBy = "product", cascade = CascadeType.ALL)
	private List<ProductVariations> variations = new ArrayList<>();

	@Builder.Default
	@ManyToMany
	@JoinTable(name = "cibus_product_subcategory",
		joinColumns = @JoinColumn(name = "productId"), 
		inverseJoinColumns = @JoinColumn(name = "subCategoryId"))
	private List<SubCategory> subCategories = new ArrayList<>();

	public void addSubCategory(SubCategory subCategory) {
		subCategories.add(subCategory);
	}

	public void addSubCategories(List<SubCategory> subCategory) {
		this.subCategories.addAll(subCategory);
	}

	public boolean isInStockMinimum(){
		return getStockQuantity() <= getMinimumStock();
	}
}
