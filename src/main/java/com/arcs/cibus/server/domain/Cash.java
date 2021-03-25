package com.arcs.cibus.server.domain;

import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.CashSerializer;
import com.arcs.cibus.server.serializer.ProductSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

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
@JsonSerialize(using = CashSerializer.class)
@Entity(name = "cibus_cashs")
public class Cash implements Serializable {

	private static final long serialVersionUID = 1L;    
	@Transient
	@Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.SIMPLE;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private String description;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	private Date openDate;
	private Date closeDate;

	private BigDecimal startValue;
	private BigDecimal currentValue;
}
