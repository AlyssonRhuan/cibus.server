package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
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
		jsonGenerator.writeStringField("name", user.getName());
		jsonGenerator.writeStringField("email", user.getEmail());
		jsonGenerator.writeStringField("login", user.getLogin());
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final User user, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", user.getId());
		jsonGenerator.writeStringField("name", user.getName());
		jsonGenerator.writeStringField("email", user.getEmail());
		jsonGenerator.writeStringField("login", user.getLogin());
		jsonGenerator.writeStringField("pass", user.getPass());
		jsonGenerator.writeBooleanField("actionRead", user.getActionRead());
		jsonGenerator.writeBooleanField("actionAdd", user.getActionAdd());
		jsonGenerator.writeBooleanField("actionUpdate", user.getActionUpdate());
		jsonGenerator.writeBooleanField("actionRemove", user.getActionRemove());
		
		user.getProfile().setTipoSerializer(TipoSerializer.VALUELABEL);
		jsonGenerator.writeObjectField("profile", user.getProfile());
		
		jsonGenerator.writeEndObject();
	}
}
