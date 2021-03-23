package com.arcs.cibus.server.serializer;

import com.arcs.cibus.server.domain.*;
import com.arcs.cibus.server.domain.enums.SaleStatus;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.IOException;
import java.math.BigDecimal;

public class SaleSerializer extends JsonSerializer<Sale> {

	@Override
	public void serialize(final Sale sale,
						  final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {		
		switch(sale.getTipoSerializer()) {
			case SIMPLE:
				serializerSimples(sale, jsonGenerator, serializers);
				break;
			case FULL:			
				serializerCompleta(sale, jsonGenerator, serializers);
				break;
		}
	}
	
	private void serializerSimples(final Sale sale,
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();

		jsonGenerator.writeNumberField("id", sale.getId());
		jsonGenerator.writeNumberField("quantity", sale.getQuantity());
		jsonGenerator.writeNumberField("price", sale.getPrice());
		jsonGenerator.writeStringField("saleDate", sale.getSaleDate().toString());
		jsonGenerator.writeStringField("saleStatus", sale.getSaleStatus().name());

		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final Sale sale,
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();

		jsonGenerator.writeNumberField("id", sale.getId());
		jsonGenerator.writeNumberField("quantity", sale.getQuantity());
		jsonGenerator.writeNumberField("price", sale.getPrice());
		jsonGenerator.writeStringField("saleDate", sale.getSaleDate().toString());
		jsonGenerator.writeStringField("saleStatus", sale.getSaleStatus().name());

		jsonGenerator.writeFieldName("product");
		sale.getProduct().setTipoSerializer(TipoSerializer.SIMPLE);
		jsonGenerator.writeObject(sale.getProduct());

//		jsonGenerator.writeFieldName("client");
//		sale.getClient().setTipoSerializer(TipoSerializer.SIMPLE);
//		jsonGenerator.writeObject(sale.getClient());
		
		jsonGenerator.writeEndObject();
	}
}
