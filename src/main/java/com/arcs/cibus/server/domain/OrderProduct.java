package com.arcs.cibus.server.domain;

import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.OrderProductSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonSerialize (using = OrderProductSerializer.class)
@Table
@Entity (name = "cibus_orders_products")
public class OrderProduct implements Serializable
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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn (name = "order_id")
    private Order order;
}
