package com.arcs.cibus.server.serializer;

import com.arcs.cibus.server.domain.Report;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Map;

public class ReportSerializer extends JsonSerializer<Report> {

	@Override
	public void serialize(final Report report,
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {		
		switch(report.getTipoSerializer()) {
			case SIMPLE:
				serializerSimples(report, jsonGenerator, serializers);
				break;
			case FULL:			
				serializerCompleta(report, jsonGenerator, serializers);
				break;
		}
	}
	
	private void serializerSimples(final Report report,
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", report.getId());
		jsonGenerator.writeStringField("title", report.getTitle());
		jsonGenerator.writeStringField("request", report.getRequest());
		jsonGenerator.writeStringField("icon", report.getIcon());
		jsonGenerator.writeStringField("prefix", report.getPrefix());
		jsonGenerator.writeStringField("suffix", report.getSuffix());
		jsonGenerator.writeNumberField("size", report.getSize());
		jsonGenerator.writeBooleanField("isFav", report.getIsFav());

		jsonGenerator.writeStringField("type", report.getType().toString());
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final Report report,
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();

		jsonGenerator.writeNumberField("id", report.getId());
		jsonGenerator.writeStringField("title", report.getTitle());
		jsonGenerator.writeStringField("request", report.getRequest());
		jsonGenerator.writeStringField("icon", report.getIcon());
		jsonGenerator.writeStringField("prefix", report.getPrefix());
		jsonGenerator.writeStringField("suffix", report.getSuffix());
		jsonGenerator.writeNumberField("size", report.getSize());
		jsonGenerator.writeBooleanField("isFav", report.getIsFav());

		jsonGenerator.writeStringField("type", report.getType().toString());

		jsonGenerator.writeArrayFieldStart("data");
		for(Map.Entry<String, String> t : report.getData().entrySet()){
			jsonGenerator.writeStartObject();
			jsonGenerator.writeStringField("key", t.getKey());
			jsonGenerator.writeStringField("value", t.getValue());
			jsonGenerator.writeEndObject();
		}
		jsonGenerator.writeEndArray();

		jsonGenerator.writeEndObject();
	}
}
