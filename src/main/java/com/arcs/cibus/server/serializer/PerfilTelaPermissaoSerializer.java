package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.PerfilTelaPermissao;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PerfilTelaPermissaoSerializer extends JsonSerializer<PerfilTelaPermissao> {

	@Override
	public void serialize(final PerfilTelaPermissao telaPermissao, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers)
			throws IOException {		
		switch(telaPermissao.getTipoSerializer()) {
			case SIMPLES:
				serializerSimples(telaPermissao, jsonGenerator, serializers);
				break;
			case COMPLETA:			
				serializerCompleta(telaPermissao, jsonGenerator, serializers);
				break;
		default:
			break;
		}
	}
	
	private void serializerSimples(final PerfilTelaPermissao telaPermissao, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", telaPermissao.getPerfilTelaPermissaoID());
		jsonGenerator.writeStringField("tela", telaPermissao.getTela().getNome());
		jsonGenerator.writeStringField("perfil", telaPermissao.getPerfil().getNome());
		jsonGenerator.writeBooleanField("permissao", telaPermissao.getPermissao());
		
		jsonGenerator.writeEndObject();
	}
	
	private void serializerCompleta(final PerfilTelaPermissao telaPermissao, 
			final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException {
		jsonGenerator.writeStartObject();
		
		jsonGenerator.writeNumberField("id", telaPermissao.getPerfilTelaPermissaoID());
		jsonGenerator.writeObjectField("tela", telaPermissao.getTela());
		jsonGenerator.writeObjectField("perfil", telaPermissao.getPerfil());
		jsonGenerator.writeBooleanField("permissao", telaPermissao.getPermissao());	
		
		jsonGenerator.writeEndObject();
	}
}
