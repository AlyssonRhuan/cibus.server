package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.Category;
import com.arcs.cibus.server.domain.Product;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ProductSerializer extends JsonSerializer<Product>
{

    @Override
    public void serialize(final Product product,
                          final JsonGenerator jsonGenerator, final SerializerProvider serializers)
            throws IOException
    {
        switch (product.getTipoSerializer())
        {
            case SIMPLE:
                serializerSimples(product, jsonGenerator, serializers);
                break;
            case FULL:
                serializerCompleta(product, jsonGenerator, serializers);
                break;
            default:
                break;
        }
    }

    private void serializerSimples(final Product product,
                                   final JsonGenerator jsonGenerator, final SerializerProvider serializers)
            throws IOException
    {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", product.getId());
        jsonGenerator.writeStringField("name", product.getName());
        jsonGenerator.writeNumberField("price", product.getPrice());
        jsonGenerator.writeBooleanField("visible", product.getVisible());
        jsonGenerator.writeBooleanField("isActive", product.getIsActive());
        jsonGenerator.writeStringField("description", product.getDescription());
        jsonGenerator.writeStringField("quickDescription", product.getQuickDescription());

        jsonGenerator.writeEndObject();
    }

    private void serializerCompleta(final Product product,
                                    final JsonGenerator jsonGenerator, final SerializerProvider serializers)
            throws IOException
    {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", product.getId());
        jsonGenerator.writeStringField("name", product.getName());
        jsonGenerator.writeBooleanField("visible", product.getVisible());
        jsonGenerator.writeBooleanField("isActive", product.getIsActive());
        jsonGenerator.writeStringField("description", product.getDescription());
        jsonGenerator.writeStringField("quickDescription", product.getQuickDescription());
        jsonGenerator.writeStringField("image", product.getImage());
        jsonGenerator.writeNumberField("price", product.getPrice());
        jsonGenerator.writeNumberField("stockQuantity", product.getStockQuantity());
        jsonGenerator.writeNumberField("minimumStock", product.getMinimumStock());

        jsonGenerator.writeArrayFieldStart("categorys");
        for (final Category category : product.getCategorys())
        {
            category.setTipoSerializer(TipoSerializer.VALUELABEL);
            jsonGenerator.writeObject(category);
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
