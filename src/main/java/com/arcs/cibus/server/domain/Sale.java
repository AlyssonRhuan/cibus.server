package com.arcs.cibus.server.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.arcs.cibus.server.domain.enums.SaleStatus;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.serializer.SaleSerializer;
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
@JsonSerialize (using = SaleSerializer.class)
@Table
@Entity (name = "cibus_sales")
public class Sale implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Transient
    @Builder.Default
    private TipoSerializer tipoSerializer = TipoSerializer.FULL;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private Date saleDate;

    @Enumerated (EnumType.STRING)
    private SaleStatus saleStatus;

    @OneToOne
    @JoinColumn (name = "client_id", referencedColumnName = "id")
    private User client;

    @OneToOne
    @JoinColumn (name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @OneToOne
    @JoinColumn (name = "cash_id", referencedColumnName = "id")
    private Cash cash;

    @Builder.Default
    @OneToMany (mappedBy = "sale", cascade = CascadeType.ALL)
    private Set<SaleProduct> saleProducts = new HashSet<>();
}
