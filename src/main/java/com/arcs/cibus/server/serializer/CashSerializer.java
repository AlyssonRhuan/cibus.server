package com.arcs.cibus.server.serializer;

import com.arcs.cibus.server.domain.Cash;
import com.arcs.cibus.server.domain.Category;
import com.arcs.cibus.server.domain.Product;
import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

public class CashSerializer extends JsonSerializer<Cash> {

	@Override
	public void serialize(final Cash cash,
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {
		switch(cash.getTipoSerializer()) {
			case SIMPLE:
				serializerSimples(cash, jsonGenerator, serializers);
				break;
			case FULL:
				serializerCompleta(cash, jsonGenerator, serializers);
				break;
		default:
			break;
		}
	}
	
	private void serializerSimples(final Cash cash,
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {				
		jsonGenerator.writeStartObject();

		jsonGenerator.writeNumberField("id", cash.getId());
		jsonGenerator.writeStringField("description", cash.getDescription());
		jsonGenerator.writeStringField("openDate", cash.getOpenDate().toString());
		jsonGenerator.writeStringField("closeDate", cash.getCloseDate() == null ? "" : cash.getCloseDate().toString());
		jsonGenerator.writeNumberField("startValue", cash.getStartValue());
		jsonGenerator.writeNumberField("currentValue", cash.getCurrentValue());
		jsonGenerator.writeStringField("user", cash.getUser().getName());

		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final Cash cash,
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {				
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", cash.getId());
		jsonGenerator.writeStringField("description", cash.getDescription());
		jsonGenerator.writeStringField("openDate", cash.getOpenDate().toString());
		jsonGenerator.writeStringField("closeDate", cash.getCloseDate() == null ? "" : cash.getCloseDate().toString());
		jsonGenerator.writeNumberField("startValue", cash.getStartValue());
		jsonGenerator.writeNumberField("currentValue", cash.getCurrentValue());

		jsonGenerator.writeFieldName("user");
		cash.getUser().setTipoSerializer(TipoSerializer.SIMPLE);
		jsonGenerator.writeObject(cash.getUser());
		
		jsonGenerator.writeEndObject();
	}
}
