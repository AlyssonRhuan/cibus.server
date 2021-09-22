package com.arcs.cibus.server.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.SaleProductSerializer;
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
@JsonSerialize (using = SaleProductSerializer.class)
@Table
@Entity (name = "cibus_sales_products")
public class SaleProduct implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Transient
    @Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.FULL;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private Long quantity;
    private BigDecimal price;

    @OneToOne
    @JoinColumn (name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn (name = "sale_id", referencedColumnName = "id")
    private Sale sale;
}
