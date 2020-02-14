package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.Tela;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class UsuarioSerializer extends JsonSerializer<Tela> {

	@Override
	public void serialize(final Tela tela, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {		
		switch(tela.getTipoSerializer()) {
			case SIMPLES:
				serializerSimples(tela, jsonGenerator, serializers);
				break;
			case COMPLETA:			
				serializerCompleta(tela, jsonGenerator, serializers);
				break;
		default:
			break;
		}
	}
	
	private void serializerSimples(final Tela tela, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", tela.getId());
		jsonGenerator.writeStringField("nome", tela.getNome());
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final Tela tela, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", tela.getId());
		jsonGenerator.writeStringField("nome", tela.getNome());
		jsonGenerator.writeStringField("caminho", tela.getCaminho());
		
		jsonGenerator.writeEndObject();
	}
}
