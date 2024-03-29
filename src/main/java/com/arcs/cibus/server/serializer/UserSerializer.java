package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.domain.enums.Profile;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class UserSerializer extends JsonSerializer<User> {

	@Override
	public void serialize(final User user, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {		
		switch(user.getTipoSerializer()) {
			case SIMPLE:
				serializerSimples(user, jsonGenerator, serializers);
				break;
			case FULL:			
				serializerCompleta(user, jsonGenerator, serializers);
				break;
		default:
			break;
		}
	}
	
	private void serializerSimples(final User user, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", user.getId());
		jsonGenerator.writeBooleanField("firstLogin", user.isFirstLogin());
		jsonGenerator.writeStringField("name", user.getName());
		jsonGenerator.writeStringField("login", user.getLogin());
		jsonGenerator.writeStringField("profile", user.getProfile().name());
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final User user, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", user.getId());
		jsonGenerator.writeBooleanField("firstLogin", user.isFirstLogin());
		jsonGenerator.writeStringField("name", user.getName());
		jsonGenerator.writeStringField("login", user.getLogin());
		jsonGenerator.writeStringField("profile", user.getProfile().name());
		
		jsonGenerator.writeEndObject();
	}
}
