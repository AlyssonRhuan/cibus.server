package com.arcs.cibus.server.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.domain.enums.ViewContext;
import com.arcs.cibus.server.serializer.ViewSerializer;
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
@JsonSerialize(using = ViewSerializer.class)
@Entity(name = "cibus_views")
public class View implements Serializable {

	private static final long serialVersionUID = 1L;    
	@Transient
	@Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.FULL;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private String path;
	private String name;
	private ViewContext context;

	/**
	 * LEVEL 1 - FOR ADMIN USER (ALL ACCESS, LEVEL 1, 2 AND 3)
	 * LEVEL 2 - FOR SALESMAN USER (ACCESS VIEW FROM LEVEL 2 AND LEVEL 3)
	 * LEVEL 3 - FOR CLIENT USER (ACCESS VIEW FROM LEVEL 3)
	 */
	private Integer level;
}
