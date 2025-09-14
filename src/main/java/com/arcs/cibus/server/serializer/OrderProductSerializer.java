package com.arcs.cibus.server.serializer;

import com.arcs.cibus.server.domain.OrderProduct;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class OrderProductSerializer extends JsonSerializer<OrderProduct>
{

    @Override
    public void serialize(final OrderProduct orderProduct,
                          final JsonGenerator jsonGenerator, final SerializerProvider serializers)
            throws IOException
    {
        switch (orderProduct.getTipoSerializer())
        {
            case SIMPLE:
                serializerSimples(orderProduct, jsonGenerator, serializers);
                break;
            case FULL:
                serializerCompleta(orderProduct, jsonGenerator, serializers);
                break;
        }
    }

    private void serializerSimples(final OrderProduct orderProduct,
                                   final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException
    {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", orderProduct.getId());
        jsonGenerator.writeNumberField("quantity", orderProduct.getQuantity());
        jsonGenerator.writeNumberField("price", orderProduct.getPrice());
        jsonGenerator.writeStringField("product", orderProduct.getProduct().getName());

        jsonGenerator.writeEndObject();
    }

    private void serializerCompleta(final OrderProduct orderProduct,
                                    final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException
    {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", orderProduct.getId());
        jsonGenerator.writeNumberField("quantity", orderProduct.getQuantity());
        jsonGenerator.writeNumberField("price", orderProduct.getPrice());

        jsonGenerator.writeFieldName("product");
        orderProduct.getProduct().setTipoSerializer(TipoSerializer.SIMPLE);
        jsonGenerator.writeObject(orderProduct.getProduct());

        jsonGenerator.writeEndObject();
    }
}
