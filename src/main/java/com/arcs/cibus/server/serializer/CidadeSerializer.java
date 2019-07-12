package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.Cidade;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CidadeSerializer extends JsonSerializer<Cidade> {

	@Override
	public void serialize(final Cidade cidade, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {		
		switch(cidade.getTipoSerializer()) {
			case SIMPLES:
				serializerSimples(cidade, jsonGenerator, serializers);
				break;
			case COMPLETA:			
				serializerCompleta(cidade, jsonGenerator, serializers);
				break;
		}
	}
		
	private void serializerSimples(final Cidade cidade, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {				
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", cidade.getCidadeID());
		jsonGenerator.writeStringField("nome", cidade.getNome());	
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final Cidade cidade, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {				
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", cidade.getCidadeID());
		jsonGenerator.writeStringField("nome", cidade.getNome());	
		
		jsonGenerator.writeEndObject();
	}
}
