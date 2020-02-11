package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.Categoria;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CategoriaSerializer extends JsonSerializer<Categoria> {

	@Override
	public void serialize(final Categoria categoria, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {		
		switch(categoria.getTipoSerializer()) {
			case SIMPLES:
				serializerSimples(categoria, jsonGenerator, serializers);
				break;
			case COMPLETA:			
				serializerCompleta(categoria, jsonGenerator, serializers);
				break;
			case VALUELABEL:			
				serializerValueLabel(categoria, jsonGenerator, serializers);
				break;
		}
	}
	
	private void serializerSimples(final Categoria categoria, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", categoria.getCategoriaID());
		jsonGenerator.writeStringField("nome", categoria.getNome());
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final Categoria categoria, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", categoria.getCategoriaID());
		jsonGenerator.writeStringField("nome", categoria.getNome());
		jsonGenerator.writeStringField("descricao", categoria.getDescricao());
		jsonGenerator.writeBooleanField("ativo", categoria.getAtivo());
		jsonGenerator.writeStringField("icone", categoria.getIcone());
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerValueLabel(final Categoria categoria, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();

		jsonGenerator.writeNumberField("categoriaID", categoria.getCategoriaID());
		jsonGenerator.writeNumberField("value", categoria.getCategoriaID());
		jsonGenerator.writeStringField("label", categoria.getNome());
		
		jsonGenerator.writeEndObject();
	}
}
