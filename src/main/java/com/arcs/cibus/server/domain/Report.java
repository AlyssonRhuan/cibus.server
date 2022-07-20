package com.arcs.cibus.server.domain;

import com.arcs.cibus.server.domain.enums.ReportType;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.CashSerializer;
import com.arcs.cibus.server.serializer.ReportSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonSerialize(using = ReportSerializer.class)
@Entity(name = "cibus_reports")
public class Report implements Serializable {

	private static final long serialVersionUID = 1L;    
	@Transient
	@Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.SIMPLE;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private String title;
	private String request;
	private String icon;
	private String prefix;
	private String suffix;
	private int size;
	private Boolean isFav;

	@Enumerated(EnumType.STRING)
	private ReportType type;

	@Transient
	private Map<String, String> data = new HashMap<>();
}
