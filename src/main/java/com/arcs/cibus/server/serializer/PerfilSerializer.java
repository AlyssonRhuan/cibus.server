package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.Perfil;
import com.arcs.cibus.server.domain.Tela;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PerfilSerializer extends JsonSerializer<Perfil> {

	@Override
	public void serialize(final Perfil perfil, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {		
		switch(perfil.getTipoSerializer()) {
			case SIMPLES:
				serializerSimples(perfil, jsonGenerator, serializers);
				break;
			case COMPLETA:			
				serializerCompleta(perfil, jsonGenerator, serializers);
				break;
		default:
			break;
		}
	}
	
	private void serializerSimples(final Perfil perfil, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", perfil.getPerfilID());
		jsonGenerator.writeStringField("nome", perfil.getNome());
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final Perfil perfil, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", perfil.getPerfilID());
		jsonGenerator.writeStringField("nome", perfil.getNome());	
		
		jsonGenerator.writeArrayFieldStart("telas");
		for(final Tela tela : perfil.getTelas()) {
			tela.setTipoSerializer(TipoSerializer.SIMPLES);
			jsonGenerator.writeObject(tela);
		}
		jsonGenerator.writeEndArray();		
		
		jsonGenerator.writeEndObject();
	}
}
