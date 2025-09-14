package com.arcs.cibus.server.serializer;

import com.arcs.cibus.server.domain.*;
import com.arcs.cibus.server.domain.enums.OrderStatus;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.Builder;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class OrderSerializer extends JsonSerializer<Order>
{

    @Override
    public void serialize(final Order order,
                          final JsonGenerator jsonGenerator, final SerializerProvider serializers)
            throws IOException
    {
        switch (order.getTipoSerializer())
        {
            case SIMPLE:
                serializerSimples(order, jsonGenerator, serializers);
                break;
            case FULL:
                serializerCompleta(order, jsonGenerator, serializers);
                break;
            default:
                break;
        }
    }

    private void serializerSimples(final Order order,
                                   final JsonGenerator jsonGenerator, final SerializerProvider serializers)
            throws IOException
    {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", order.getId());
        jsonGenerator.writeNumberField("cardOrderId", order.getCardOrderId());
        jsonGenerator.writeStringField("orderDate", order.getOrderDate().toString());
        jsonGenerator.writeNumberField("tableId", order.getTableId());
        jsonGenerator.writeStringField("orderStatus", order.getOrderStatus().name());

        jsonGenerator.writeEndObject();
    }

    private void serializerCompleta(final Order order,
                                    final JsonGenerator jsonGenerator, final SerializerProvider serializers)
            throws IOException
    {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", order.getId());
        jsonGenerator.writeNumberField("cardOrderId", order.getCardOrderId());
        jsonGenerator.writeStringField("orderDate", order.getOrderDate().toString());
        jsonGenerator.writeNumberField("tableId", order.getTableId());
        jsonGenerator.writeStringField("orderStatus", order.getOrderStatus().name());

        jsonGenerator.writeArrayFieldStart("orderProducts");

        if (order.getOrderProducts() != null && !order.getOrderProducts().isEmpty())
        {
            for (OrderProduct orderProduct : order.getOrderProducts())
            {
                orderProduct.setTipoSerializer(TipoSerializer.FULL);
                jsonGenerator.writeObject(orderProduct);
            }
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
