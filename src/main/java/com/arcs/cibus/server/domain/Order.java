package com.arcs.cibus.server.domain;

import com.arcs.cibus.server.domain.enums.OrderStatus;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.OrderSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@Table
@JsonSerialize (using = OrderSerializer.class)
@Entity(name = "cibus_order")
public class Order implements Serializable {

    public Order(){}

    private static final long serialVersionUID = 1L;
    @Transient
    @Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.FULL;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private Long cardOrderId;
    private Date orderDate;
    private Long tableId;

    @Builder.Default
    @OneToMany (mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts;

    @Enumerated (EnumType.STRING)
    private OrderStatus orderStatus;
}