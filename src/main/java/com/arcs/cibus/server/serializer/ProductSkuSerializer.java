package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.ProductSku;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ProductSkuSerializer extends JsonSerializer<ProductSku> {

	@Override
	public void serialize(final ProductSku productSku, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {
		switch(productSku.getTipoSerializer()) {
			case SIMPLE:
				serializerSimples(productSku, jsonGenerator, serializers);
				break;
			case FULL:			
				serializerCompleta(productSku, jsonGenerator, serializers);
				break;
		default:
			break;
		}
	}
	
	private void serializerSimples(final ProductSku productSku, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {				
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", productSku.getId());
		jsonGenerator.writeNumberField("price", productSku.getPrice());	
		jsonGenerator.writeStringField("colorName", productSku.getColorName());	
		jsonGenerator.writeStringField("colorCode", productSku.getColorCode());	
		jsonGenerator.writeStringField("size", productSku.getSize());	
		jsonGenerator.writeNumberField("stockQuantity", productSku.getStockQuantity());	
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final ProductSku productSku, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {				
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", productSku.getId());
		jsonGenerator.writeStringField("image", productSku.getImage());
		jsonGenerator.writeNumberField("price", productSku.getPrice());	
		jsonGenerator.writeStringField("colorName", productSku.getColorName());	
		jsonGenerator.writeStringField("colorCode", productSku.getColorCode());	
		jsonGenerator.writeStringField("size", productSku.getSize());	
		jsonGenerator.writeNumberField("stockQuantity", productSku.getStockQuantity());	
		
		jsonGenerator.writeEndObject();
	}
}
