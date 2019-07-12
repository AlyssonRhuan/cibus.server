package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.Cliente;
import com.arcs.cibus.server.domain.Endereco;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ClienteSerializer extends JsonSerializer<Cliente> {

	@Override
	public void serialize(final Cliente cliente, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {	
		switch(cliente.getTipoSerializer()) {
			case SIMPLES:
				serializerSimples(cliente, jsonGenerator, serializers);
				break;
			case COMPLETA:			
				serializerCompleta(cliente, jsonGenerator, serializers);
				break;
		}
	}
	
	private void serializerSimples(final Cliente cliente, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {				
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", cliente.getClienteID());
		jsonGenerator.writeStringField("nome", cliente.getNome());
		jsonGenerator.writeStringField("email", cliente.getEmail());		
		
		jsonGenerator.writeEndObject();
	}
		
	private void serializerCompleta(final Cliente cliente, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {				
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", cliente.getClienteID());
		jsonGenerator.writeStringField("nome", cliente.getNome());
		jsonGenerator.writeStringField("email", cliente.getEmail());
		jsonGenerator.writeStringField("cpfCnpj", cliente.getCpfCnpj());
		jsonGenerator.writeStringField("tipoCliente", cliente.getTipoCliente().getLabel());
		
		jsonGenerator.writeArrayFieldStart("enderecos");
		for(final Endereco endereco : cliente.getEnderecos()) {
			jsonGenerator.writeObject(endereco);
		}
		jsonGenerator.writeEndArray();
		
		jsonGenerator.writeArrayFieldStart("telefones");
		for(final String telefone : cliente.getTelefones()) {
			jsonGenerator.writeObject(telefone);
		}
		jsonGenerator.writeEndArray();
		
		jsonGenerator.writeEndObject();
	}
}
