package com.arcs.cibus.server.serializer;

import com.arcs.cibus.server.domain.Category;
import com.arcs.cibus.server.domain.Dashboard;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DashboardSerializer extends JsonSerializer<Dashboard> {

	@Override
	public void serialize(final Dashboard dashboard,
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {		
		switch(dashboard.getTipoSerializer()) {
			case SIMPLE:
				serializerSimples(dashboard, jsonGenerator, serializers);
				break;
			case FULL:			
				serializerCompleta(dashboard, jsonGenerator, serializers);
				break;
		}
	}
	
	private void serializerSimples(final Dashboard dashboard,
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("salesTotal", dashboard.getSalesTotal());
		jsonGenerator.writeNumberField("ordersOpenned", dashboard.getOrdersOpenned());
		jsonGenerator.writeNumberField("ordersClosed", dashboard.getOrdersClosed());
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final Dashboard dashboard,
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();

		jsonGenerator.writeNumberField("salesTotal", dashboard.getSalesTotal());
		jsonGenerator.writeNumberField("ordersOpenned", dashboard.getOrdersOpenned());
		jsonGenerator.writeNumberField("ordersClosed", dashboard.getOrdersClosed());

		jsonGenerator.writeArrayFieldStart("products");
		for(Map.Entry<String, Long> t : dashboard.getQuantityProducts().entrySet()){
			jsonGenerator.writeStartObject();
			jsonGenerator.writeStringField("product", t.getKey());
			jsonGenerator.writeNumberField("quantity", t.getValue());
			jsonGenerator.writeEndObject();
		}
		jsonGenerator.writeEndArray();

		jsonGenerator.writeArrayFieldStart("categories");
		for(Map.Entry<String, Double> t : dashboard.getPercentCategories().entrySet()){
			jsonGenerator.writeStartObject();
			jsonGenerator.writeStringField("item", t.getKey());
			jsonGenerator.writeNumberField("percent", (double) Math.round(t.getValue() * 100) / 100);
			jsonGenerator.writeEndObject();
		}
		jsonGenerator.writeEndArray();

		jsonGenerator.writeEndObject();
	}
}
