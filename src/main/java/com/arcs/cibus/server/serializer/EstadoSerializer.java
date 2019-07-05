package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.Estado;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class EstadoSerializer extends JsonSerializer<Estado> {

	@Override
	public void serialize(final Estado estado, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeNumberField("id", estado.getEstadoId());
		jsonGenerator.writeStringField("nome", estado.getNome());		
		jsonGenerator.writeEndObject();
	}
}
