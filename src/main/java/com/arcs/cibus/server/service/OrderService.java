package com.arcs.cibus.server.service;

import com.arcs.cibus.server.domain.Order;
import com.arcs.cibus.server.domain.OrderProduct;
import com.arcs.cibus.server.domain.Product;
import com.arcs.cibus.server.domain.SaleProduct;
import com.arcs.cibus.server.domain.enums.OrderStatus;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.repository.OrderProductRepository;
import com.arcs.cibus.server.repository.OrderRepository;
import com.arcs.cibus.server.service.exceptions.OrderNotClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(long orderId) {
        return orderRepository.getById(orderId);
    }

    public Order openOrder(long cardOrderId, long tableId) {
        List<Order> openOrders = orderRepository.getOpenOrderById(cardOrderId);

        if(openOrders.size() > 0)
        {
            throw new OrderNotClosedException("Essa comanda ainda está em aberto.");
        }

        Order order = new Order();
        order.setOrderStatus(OrderStatus.OPEN);
        order.setCardOrderId(cardOrderId);
        order.setTableId(tableId);
        order.setOrderDate(new Date());
        return orderRepository.save(order);
    }

    public Order checkoutOrder(long orderId) {
        Order order = orderRepository.getById(orderId);
        order.setOrderStatus(OrderStatus.CLOSED);
        return orderRepository.save(order);
    }

    public Order addProductsInOrder(long cardOrderId, long tableId, List<OrderProduct> orderProducts) {
        Order order = orderRepository.getOrderByCardOrderIdAndTableId(cardOrderId, tableId);
        order.setTipoSerializer(TipoSerializer.FULL);
        orderRepository.save(order);

        for (OrderProduct orderProduct : orderProducts)
        {
            orderProduct.setOrder(order);
            orderProductRepository.save(orderProduct);
        }

        order.getOrderProducts().addAll(orderProducts);

        return order;
    }

    public Order getByCardOrderIdAndTableId(long cardOrderId, long tableId) {
        Order order = orderRepository.getOrderByCardOrderIdAndTableId(cardOrderId, tableId);
        order.setTipoSerializer(TipoSerializer.FULL);
        return order;
    }
}
