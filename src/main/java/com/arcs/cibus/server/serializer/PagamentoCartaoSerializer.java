package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.PagamentoCartao;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PagamentoCartaoSerializer extends JsonSerializer<PagamentoCartao> {

	@Override
	public void serialize(final PagamentoCartao pagamento, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {		
		switch(pagamento.getTipoSerializer()) {
			case SIMPLES:
				serializerSimples(pagamento, jsonGenerator, serializers);
				break;
			case COMPLETA:			
				serializerCompleta(pagamento, jsonGenerator, serializers);
				break;
		}
	}
	
	private void serializerSimples(final PagamentoCartao pagamento, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", pagamento.getPagamentoID());
		jsonGenerator.writeStringField("estado", pagamento.getEstado().getLabel());
		jsonGenerator.writeNumberField("parcelas", pagamento.getNumeroParcelas());
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final PagamentoCartao pagamento, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", pagamento.getPagamentoID());
		jsonGenerator.writeStringField("estado", pagamento.getEstado().getLabel());
		
		jsonGenerator.writeFieldName("pedido");
		jsonGenerator.writeObject(pagamento.getPedido());
		
		jsonGenerator.writeNumberField("parcelas", pagamento.getNumeroParcelas());
		
		jsonGenerator.writeEndObject();
	}
}
