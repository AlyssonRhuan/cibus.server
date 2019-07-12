package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.Endereco;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class EnderecoSerializer extends JsonSerializer<Endereco> {

	@Override
	public void serialize(final Endereco endereco, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {
		switch(endereco.getTipoSerializer()) {
			case SIMPLES:
				serializerSimples(endereco, jsonGenerator, serializers);
				break;
			case COMPLETA:			
				serializerCompleta(endereco, jsonGenerator, serializers);
				break;
		}
	}
	
	private void serializerSimples(final Endereco endereco, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {				
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", endereco.getEnderecoID());
		jsonGenerator.writeStringField("logradouro", endereco.getLogradouro());
		jsonGenerator.writeStringField("numero", endereco.getNumero());
		jsonGenerator.writeStringField("bairro", endereco.getBairro());		
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final Endereco endereco, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {				
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", endereco.getEnderecoID());
		jsonGenerator.writeStringField("logradouro", endereco.getLogradouro());
		jsonGenerator.writeStringField("numero", endereco.getNumero());
		jsonGenerator.writeStringField("complemento", endereco.getComplemento());
		jsonGenerator.writeStringField("bairro", endereco.getBairro());		
		jsonGenerator.writeStringField("cep", endereco.getCep());	
		
		jsonGenerator.writeObject(endereco.getCidade());		
		jsonGenerator.writeObject(endereco.getCliente());
		
		jsonGenerator.writeEndObject();
	}
}