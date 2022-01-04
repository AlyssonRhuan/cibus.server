package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.Sale;
import com.arcs.cibus.server.domain.SaleProduct;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class SaleProductSerializer extends JsonSerializer<SaleProduct>
{

    @Override
    public void serialize(final SaleProduct saleProduct,
                          final JsonGenerator jsonGenerator, final SerializerProvider serializers)
            throws IOException
    {
        switch (saleProduct.getTipoSerializer())
        {
            case SIMPLE:
                serializerSimples(saleProduct, jsonGenerator, serializers);
                break;
            case FULL:
                serializerCompleta(saleProduct, jsonGenerator, serializers);
                break;
        }
    }

    private void serializerSimples(final SaleProduct saleProduct,
                                   final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException
    {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", saleProduct.getId());
        jsonGenerator.writeNumberField("quantity", saleProduct.getQuantity());
        jsonGenerator.writeNumberField("price", saleProduct.getPrice());
        jsonGenerator.writeStringField("product", saleProduct.getProduct().getName());

        jsonGenerator.writeEndObject();
    }

    private void serializerCompleta(final SaleProduct saleProduct,
                                    final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException
    {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", saleProduct.getId());
        jsonGenerator.writeNumberField("quantity", saleProduct.getQuantity());
        jsonGenerator.writeNumberField("price", saleProduct.getPrice());

        jsonGenerator.writeFieldName("product");
        saleProduct.getProduct().setTipoSerializer(TipoSerializer.SIMPLE);
        jsonGenerator.writeObject(saleProduct.getProduct());

        jsonGenerator.writeEndObject();
    }
}
