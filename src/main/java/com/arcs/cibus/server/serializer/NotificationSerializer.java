package com.arcs.cibus.server.serializer;

import com.arcs.cibus.server.domain.Cash;
import com.arcs.cibus.server.domain.Notification;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.aspectj.weaver.ast.Not;

import java.io.IOException;

public class NotificationSerializer extends JsonSerializer<Notification> {

	@Override
	public void serialize(final Notification notification,
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {
		switch(notification.getTipoSerializer()) {
			case SIMPLE:
				serializerSimples(notification, jsonGenerator, serializers);
				break;
			case FULL:			
				serializerCompleta(notification, jsonGenerator, serializers);
				break;
		default:
			break;
		}
	}
	
	private void serializerSimples(final Notification notification,
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {				
		jsonGenerator.writeStartObject();

		jsonGenerator.writeNumberField("id", notification.getId());
		jsonGenerator.writeStringField("notification", notification.getNotification());
		jsonGenerator.writeStringField("date", notification.getDate().toString());

		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final Notification notification,
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {				
		jsonGenerator.writeStartObject();

		jsonGenerator.writeNumberField("id", notification.getId());
		jsonGenerator.writeStringField("notification", notification.getNotification());
		jsonGenerator.writeStringField("date", notification.getDate().toString());
		
		jsonGenerator.writeEndObject();
	}
}
