package com.arcs.cibus.server.serializer;

import java.io.IOException;

import com.arcs.cibus.server.domain.Category;
import com.arcs.cibus.server.domain.Payment;
import com.arcs.cibus.server.domain.Product;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PaymentSerializer extends JsonSerializer<Payment>
{

    @Override
    public void serialize(final Payment payment,
                          final JsonGenerator jsonGenerator, final SerializerProvider serializers)
            throws IOException
    {
        switch (payment.getTipoSerializer())
        {
            case SIMPLE:
                serializerSimples(payment, jsonGenerator, serializers);
                break;
            case FULL:
                serializerCompleta(payment, jsonGenerator, serializers);
                break;
            default:
                break;
        }
    }

    private void serializerSimples(final Payment payment,
                                   final JsonGenerator jsonGenerator, final SerializerProvider serializers)
            throws IOException
    {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", payment.getId());
        jsonGenerator.writeStringField("payment", payment.getPayment());
        jsonGenerator.writeBooleanField("visible", payment.getVisible());
        jsonGenerator.writeBooleanField("isCashMoviment", payment.getIsCashMoviment());

        jsonGenerator.writeEndObject();
    }

    private void serializerCompleta(final Payment payment,
                                    final JsonGenerator jsonGenerator, final SerializerProvider serializers)
            throws IOException
    {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", payment.getId());
        jsonGenerator.writeStringField("payment", payment.getPayment());
        jsonGenerator.writeBooleanField("visible", payment.getVisible());
        jsonGenerator.writeBooleanField("isCashMoviment", payment.getIsCashMoviment());
        jsonGenerator.writeStringField("description", payment.getDescription());

        jsonGenerator.writeEndObject();
    }
}
