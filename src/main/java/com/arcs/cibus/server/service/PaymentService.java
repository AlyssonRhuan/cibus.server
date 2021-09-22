package com.arcs.cibus.server.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.Payment;
import com.arcs.cibus.server.repository.PaymentRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@Service
public class PaymentService
{
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAll() throws Exception
    {
        return paymentRepository.findAll();
    }

    public Payment getById(Long paymentId) throws ObjectNotFoundException
    {
        Optional<Payment> payment = paymentRepository.findById(paymentId);
        return payment.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id " + payment + " não existe."));
    }

    public void delete(Long paymentId) throws ConstraintViolationException, Exception
    {
        Payment payment = this.getById(paymentId);
        paymentRepository.delete(payment);
    }

    public Payment save(Payment payment) throws Exception
    {
        return paymentRepository.save(payment);
    }
}
