package com.arcs.cibus.server.domain;

import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.CategorySerializer;
import com.arcs.cibus.server.serializer.SubCategorySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonSerialize(using = SubCategorySerializer.class)
@Table
@Entity(name = "cibus_subcategories")
public class SubCategory implements Serializable {

	private static final long serialVersionUID = 1L;    
	@Transient
	@Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.FULL;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private String name;
	private String description;
    private Boolean active;
    private String icon;

	@Builder.Default
	@ManyToMany
	@JoinTable(name = "cibus_category_subcategory",
			joinColumns = @JoinColumn(name = "subCategoryId"),
			inverseJoinColumns = @JoinColumn(name = "categoryId"))
	private List<Category> categories = new ArrayList<>();

    @Builder.Default
	@ManyToMany(mappedBy = "subCategories")
    private List<Product> products = new ArrayList<>();
	
	public void addProduct (Product product) {
		products.add(product);
	}
	
	public void addProducts(List<Product> products) {
		this.products.addAll(products);
	}
}
	