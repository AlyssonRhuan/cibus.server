package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.Profile;
import com.arcs.cibus.server.domain.View;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ProfileSerializer extends JsonSerializer<Profile> {

	@Override
	public void serialize(final Profile profile, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {		
		switch(profile.getTipoSerializer()) {
			case SIMPLE:
				serializerSimples(profile, jsonGenerator, serializers);
				break;
			case FULL:			
				serializerCompleta(profile, jsonGenerator, serializers);
				break;
			case VALUELABEL:			
				serializerValueLabel(profile, jsonGenerator, serializers);
				break;
		}
	}
	
	private void serializerSimples(final Profile profile, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", profile.getId());
		jsonGenerator.writeStringField("name", profile.getName());
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final Profile profile, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", profile.getId());
		jsonGenerator.writeStringField("name", profile.getName());	
		
		jsonGenerator.writeArrayFieldStart("views");
		for(final View view : profile.getViews()) {
			view.setTipoSerializer(TipoSerializer.SIMPLE);
			jsonGenerator.writeObject(view);
		}
		jsonGenerator.writeEndArray();		
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerValueLabel(final Profile profile, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();

		jsonGenerator.writeNumberField("id", profile.getId());
		jsonGenerator.writeNumberField("value", profile.getId());
		jsonGenerator.writeStringField("label", profile.getName());
		
		jsonGenerator.writeEndObject();
	}
}
