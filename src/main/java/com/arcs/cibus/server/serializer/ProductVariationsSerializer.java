package com.arcs.cibus.server.serializer;

import com.arcs.cibus.server.domain.Product;
import com.arcs.cibus.server.domain.ProductVariations;
import com.arcs.cibus.server.domain.SubCategory;
import com.arcs.cibus.server.domain.enums.ProductVariationType;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ProductVariationsSerializer extends JsonSerializer<ProductVariations>
{

    @Override
    public void serialize(final ProductVariations productVariations,
                          final JsonGenerator jsonGenerator, final SerializerProvider serializers)
            throws IOException
    {
        switch (productVariations.getTipoSerializer())
        {
            case SIMPLE:
                serializerSimples(productVariations, jsonGenerator, serializers);
                break;
            case FULL:
                serializerCompleta(productVariations, jsonGenerator, serializers);
                break;
            default:
                break;
        }
    }

    private void serializerSimples(final ProductVariations productVariations,
                                   final JsonGenerator jsonGenerator, final SerializerProvider serializers)
            throws IOException
    {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", productVariations.getId());
        jsonGenerator.writeStringField("productVariationType", productVariations.getProductVariationType().toString());
        jsonGenerator.writeStringField("variation", productVariations.getVariation());
        jsonGenerator.writeNumberField("price", productVariations.getPrice());

        jsonGenerator.writeEndObject();
    }

    private void serializerCompleta(final ProductVariations productVariations,
                                    final JsonGenerator jsonGenerator, final SerializerProvider serializers)
            throws IOException
    {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", productVariations.getId());
        jsonGenerator.writeStringField("productVariationType", productVariations.getProductVariationType().toString());
        jsonGenerator.writeStringField("variation", productVariations.getVariation());
        jsonGenerator.writeNumberField("price", productVariations.getPrice());

        jsonGenerator.writeEndObject();
    }
}
