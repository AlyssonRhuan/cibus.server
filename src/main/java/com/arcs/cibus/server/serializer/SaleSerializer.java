package com.arcs.cibus.server.serializer;

import java.io.IOException;
import java.util.Map;

import com.arcs.cibus.server.domain.Sale;
import com.arcs.cibus.server.domain.SaleProduct;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class SaleSerializer extends JsonSerializer<Sale>
{

    @Override
    public void serialize(final Sale sale,
                          final JsonGenerator jsonGenerator, final SerializerProvider serializers)
            throws IOException
    {
        switch (sale.getTipoSerializer())
        {
            case SIMPLE:
                serializerSimples(sale, jsonGenerator, serializers);
                break;
            case FULL:
                serializerCompleta(sale, jsonGenerator, serializers);
                break;
        }
    }

    private void serializerSimples(final Sale sale,
                                   final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException
    {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", sale.getId());
        jsonGenerator.writeStringField("saleDate", sale.getSaleDate().toString());
        jsonGenerator.writeStringField("saleStatus", sale.getSaleStatus().name());

        jsonGenerator.writeEndObject();
    }

    private void serializerCompleta(final Sale sale,
                                    final JsonGenerator jsonGenerator, final SerializerProvider serializers) throws IOException
    {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", sale.getId());
        jsonGenerator.writeStringField("saleDate", sale.getSaleDate().toString());
        jsonGenerator.writeStringField("saleStatus", sale.getSaleStatus().name());

        jsonGenerator.writeArrayFieldStart("saleProducts");
        for (SaleProduct saleProduct : sale.getSaleProducts())
        {
            saleProduct.setTipoSerializer(TipoSerializer.SIMPLE);
            jsonGenerator.writeObject(saleProduct);
        }
        jsonGenerator.writeEndArray();

//        jsonGenerator.writeArrayFieldStart("saleProducts");
//        for (final SaleProduct saleProduct : sale.getSaleProducts())
//        {
//            saleProduct.setTipoSerializer(TipoSerializer.FULL);
//            jsonGenerator.writeObject(saleProduct);
//        }
//        jsonGenerator.writeEndArray();

//		jsonGenerator.writeFieldName("client");
//		sale.getClient().setTipoSerializer(TipoSerializer.SIMPLE);
//		jsonGenerator.writeObject(sale.getClient());

        jsonGenerator.writeFieldName("payment");
        sale.getPayment().setTipoSerializer(TipoSerializer.FULL);
        jsonGenerator.writeObject(sale.getPayment());

        jsonGenerator.writeEndObject();
    }
}
