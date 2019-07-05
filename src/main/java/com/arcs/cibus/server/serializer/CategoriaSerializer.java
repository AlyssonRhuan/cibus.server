package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.Categoria;
import com.arcs.cibus.server.domain.Produto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CategoriaSerializer extends JsonSerializer<Categoria> {

	@Override
	public void serialize(final Categoria categoria, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeNumberField("id", categoria.getCategoriaID());
		jsonGenerator.writeStringField("nome", categoria.getNome());
		jsonGenerator.writeStringField("descricao", categoria.getDescricao());
		jsonGenerator.writeBooleanField("ativo", categoria.getAtivo());
		jsonGenerator.writeStringField("icone", categoria.getIcone());
		
		jsonGenerator.writeArrayFieldStart("produtos");
		for(final Produto produto : categoria.getProdutos()) {
			jsonGenerator.writeObject(produto);
		}
		jsonGenerator.writeEndArray();
		
		jsonGenerator.writeEndObject();
	}
}
