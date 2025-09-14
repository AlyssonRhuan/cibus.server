package com.arcs.cibus.server.controller;

import com.arcs.cibus.server.domain.Order;
import com.arcs.cibus.server.domain.OrderProduct;
import com.arcs.cibus.server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping (method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getAll() throws Exception
    {
        List<Order> orders = orderService.getAll();
        return ResponseEntity.ok(orders);
    }

    @RequestMapping (value = "/{orderId}", method = RequestMethod.GET)
    public ResponseEntity<Order> getById(@PathVariable long orderId) throws Exception
    {
        Order order = orderService.getById(orderId);
        return ResponseEntity.ok(order);
    }

    @RequestMapping (value = "/{cardOrderId}/table/{tableId}", method = RequestMethod.GET)
    public ResponseEntity<Order> getByCardOrderIdAndTableId(@PathVariable long cardOrderId, @PathVariable long tableId) throws Exception
    {
        Order order = orderService.getByCardOrderIdAndTableId(cardOrderId, tableId);
        return ResponseEntity.ok(order);
    }

    @RequestMapping (value = "/{orderId}/table/{tableId}", method = RequestMethod.POST)
    public ResponseEntity<Order> openOrder(@PathVariable long orderId, @PathVariable long tableId) throws Exception
    {
        Order order = orderService.openOrder(orderId, tableId);
        return ResponseEntity.ok(order);
    }

    @RequestMapping (value = "/{cardOrderId}/table/{tableId}", method = RequestMethod.PATCH)
    public ResponseEntity<Order> addProductsInOrder(@PathVariable long cardOrderId, @PathVariable long tableId, @RequestBody List<OrderProduct> orderProducts) throws Exception
    {
        Order order = orderService.addProductsInOrder(cardOrderId, tableId, orderProducts);
        return ResponseEntity.ok(order);
    }

    @RequestMapping (value = "/{orderId}", method = RequestMethod.POST)
    public ResponseEntity<Order> checkoutOrder(@PathVariable long orderId) throws Exception
    {
        Order order = orderService.checkoutOrder(orderId);
        return ResponseEntity.ok(order);
    }
}