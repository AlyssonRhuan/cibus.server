package com.arcs.cibus.server.domain;

import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.DashboardSerializer;
import com.arcs.cibus.server.serializer.ProductSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonSerialize(using = DashboardSerializer.class)
public class Dashboard implements Serializable {

	private static final long serialVersionUID = 1L;
    @Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.FULL;

    private BigDecimal salesTotal;
    private int orders;
    private int cashs;

    @Builder.Default
    private Map<String, Double> percentCategories = new HashMap<>();
    @Builder.Default
    private Map<String, Long> quantityProducts = new HashMap<>();
}
