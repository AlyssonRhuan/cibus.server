package com.arcs.cibus.server.serializer;

import com.arcs.cibus.server.domain.Category;
import com.arcs.cibus.server.domain.SubCategory;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class SubCategorySerializer extends JsonSerializer<SubCategory> {

	@Override
	public void serialize(final SubCategory subCategory,
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {		
		switch(subCategory.getTipoSerializer()) {
			case SIMPLE:
				serializerSimples(subCategory, jsonGenerator, serializers);
				break;
			case FULL:			
				serializerCompleta(subCategory, jsonGenerator, serializers);
				break;
			case VALUELABEL:			
				serializerValueLabel(subCategory, jsonGenerator, serializers);
				break;
		}
	}
	
	private void serializerSimples(final SubCategory subCategory,
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", subCategory.getId());
		jsonGenerator.writeStringField("name", subCategory.getName());
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final SubCategory subCategory,
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", subCategory.getId());
		jsonGenerator.writeStringField("name", subCategory.getName());
		jsonGenerator.writeBooleanField("active", subCategory.getActive());
		jsonGenerator.writeStringField("icon", subCategory.getIcon());
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerValueLabel(final SubCategory subCategory,
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();

		jsonGenerator.writeNumberField("id", subCategory.getId());
		jsonGenerator.writeNumberField("value", subCategory.getId());
		jsonGenerator.writeStringField("label", subCategory.getName());
		
		jsonGenerator.writeEndObject();
	}
}
