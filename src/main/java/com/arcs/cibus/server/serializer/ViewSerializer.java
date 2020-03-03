package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.View;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ViewSerializer extends JsonSerializer<View> {

	@Override
	public void serialize(final View view, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {		
		switch(view.getTipoSerializer()) {
			case SIMPLE:
				serializerSimples(view, jsonGenerator, serializers);
				break;
			case FULL:			
				serializerCompleta(view, jsonGenerator, serializers);
				break;
		default:
			break;
		}
	}
	
	private void serializerSimples(final View view, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", view.getId());
		jsonGenerator.writeStringField("name", view.getName());
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final View view, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", view.getId());
		jsonGenerator.writeStringField("name", view.getName());
		jsonGenerator.writeStringField("path", view.getPath());
		jsonGenerator.writeNumberField("level", view.getLevel());
		jsonGenerator.writeStringField("context", view.getContext().getDescription());
		
		jsonGenerator.writeEndObject();
	}
}
