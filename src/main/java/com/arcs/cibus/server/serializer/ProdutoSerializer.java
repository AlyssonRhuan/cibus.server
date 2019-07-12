package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.Produto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ProdutoSerializer extends JsonSerializer<Produto> {

	@Override
	public void serialize(final Produto produto, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {
		switch(produto.getTipoSerializer()) {
			case SIMPLES:
				serializerSimples(produto, jsonGenerator, serializers);
				break;
			case COMPLETA:			
				serializerCompleta(produto, jsonGenerator, serializers);
				break;
		}
	}
	
	private void serializerSimples(final Produto produto, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {				
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", produto.getProdutoID());
		jsonGenerator.writeStringField("nome", produto.getNome());
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final Produto produto, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {				
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", produto.getProdutoID());
		jsonGenerator.writeStringField("nome", produto.getNome());
		jsonGenerator.writeNumberField("preco", produto.getPreco());
		jsonGenerator.writeNumberField("estoqueMinimo", produto.getEstoqueMinimo());
		jsonGenerator.writeStringField("imagem", produto.getImagem());
		jsonGenerator.writeNumberField("quantidadeEstoque", produto.getQuantidadeEstoque());
		jsonGenerator.writeBooleanField("visivel", produto.getVisivel());	
		
		jsonGenerator.writeEndObject();
	}
}
