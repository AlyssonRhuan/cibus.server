package com.arcs.cibus.server.resource;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arcs.cibus.server.domain.Payment;
import com.arcs.cibus.server.domain.Product;
import com.arcs.cibus.server.domain.enums.DomainActive;
import com.arcs.cibus.server.service.PaymentService;
import com.arcs.cibus.server.service.ProductService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping (value = "/payment")
public class PaymentResource
{

    @Autowired
    private PaymentService paymentService;

    @RequestMapping (method = RequestMethod.GET)
    public ResponseEntity<List<Payment>> getAll() throws Exception
    {
        List<Payment> Payment = paymentService.getAll();
        return ResponseEntity.ok(Payment);
    }

    @RequestMapping (value = "/{paymentId}", method = RequestMethod.GET)
    public ResponseEntity<Payment> getById(@PathVariable Long paymentId) throws ObjectNotFoundException
    {
        Payment payment = paymentService.getById(paymentId);
        return ResponseEntity.ok(payment);
    }

    @RequestMapping (value = "/{paymentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable Long paymentId) throws ConstraintViolationException, Exception
    {
        paymentService.delete(paymentId);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @RequestMapping (value = "/{paymentId}", method = RequestMethod.PUT)
    public ResponseEntity<Payment> update(@PathVariable Long paymentId, @RequestBody Payment payment) throws Exception
    {
        payment.setId(paymentId);
        payment = paymentService.save(payment);
        return ResponseEntity.ok(payment);
    }

    @RequestMapping (method = RequestMethod.POST)
    public ResponseEntity<Payment> insert(@RequestBody Payment payment) throws Exception
    {
        payment.setId(null);
        payment = paymentService.save(payment);
        return ResponseEntity.ok(payment);
    }
}
