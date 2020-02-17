package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.Category;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CategorySerializer extends JsonSerializer<Category> {

	@Override
	public void serialize(final Category category, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {		
		switch(category.getTipoSerializer()) {
			case SIMPLE:
				serializerSimples(category, jsonGenerator, serializers);
				break;
			case FULL:			
				serializerCompleta(category, jsonGenerator, serializers);
				break;
			case VALUELABEL:			
				serializerValueLabel(category, jsonGenerator, serializers);
				break;
		}
	}
	
	private void serializerSimples(final Category categoria, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", categoria.getId());
		jsonGenerator.writeStringField("name", categoria.getName());
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final Category categoria, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", categoria.getId());
		jsonGenerator.writeStringField("name", categoria.getName());
		jsonGenerator.writeStringField("description", categoria.getDescription());
		jsonGenerator.writeBooleanField("active", categoria.getActive());
		jsonGenerator.writeStringField("icon", categoria.getIcon());
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerValueLabel(final Category categoria, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();

		jsonGenerator.writeNumberField("id", categoria.getId());
		jsonGenerator.writeNumberField("value", categoria.getId());
		jsonGenerator.writeStringField("label", categoria.getName());
		
		jsonGenerator.writeEndObject();
	}
}
