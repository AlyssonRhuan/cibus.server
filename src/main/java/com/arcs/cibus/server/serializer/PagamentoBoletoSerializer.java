package com.arcs.cibus.server.serializer;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.arcs.cibus.server.domain.PagamentoBoleto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PagamentoBoletoSerializer extends JsonSerializer<PagamentoBoleto> {

	@Override
	public void serialize(final PagamentoBoleto pagamento, 
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
	
	private void serializerSimples(final PagamentoBoleto pagamento, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", pagamento.getPagamentoID());
		jsonGenerator.writeStringField("estado", pagamento.getEstado().getLabel());
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		jsonGenerator.writeStringField("data vencimento", simpleDateFormat.format(pagamento.getDataVencimento()));
		jsonGenerator.writeStringField("data pagamento", simpleDateFormat.format(pagamento.getDataPagamento()));
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final PagamentoBoleto pagamento, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", pagamento.getPagamentoID());
		jsonGenerator.writeStringField("estado", pagamento.getEstado().getLabel());
		
		jsonGenerator.writeFieldName("pedido");
		jsonGenerator.writeObject(pagamento.getPedido());
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		jsonGenerator.writeStringField("data vencimento", simpleDateFormat.format(pagamento.getDataVencimento()));
		jsonGenerator.writeStringField("data pagamento", simpleDateFormat.format(pagamento.getDataPagamento()));
		
		jsonGenerator.writeEndObject();
	}
}

