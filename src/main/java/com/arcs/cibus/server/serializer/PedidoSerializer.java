package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.Pedido;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PedidoSerializer extends JsonSerializer<Pedido> {

	@Override
	public void serialize(final Pedido pedido, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {		
		switch(pedido.getTipoSerializer()) {
			case SIMPLES:
				serializerSimples(pedido, jsonGenerator, serializers);
				break;
			case COMPLETA:			
				serializerCompleta(pedido, jsonGenerator, serializers);
				break;
		}
	}
	
	private void serializerSimples(final Pedido pedido, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", pedido.getPedidoID());
		jsonGenerator.writeStringField("instante", pedido.getInstante().toString());
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final Pedido pedido, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", pedido.getPedidoID());
		jsonGenerator.writeStringField("instante", pedido.getInstante().toString());
		
		jsonGenerator.writeEndObject();
	}
}
