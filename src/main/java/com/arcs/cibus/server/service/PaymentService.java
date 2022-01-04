package com.arcs.cibus.server.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.Category;
import com.arcs.cibus.server.domain.Payment;
import com.arcs.cibus.server.domain.enums.DomainActive;
import com.arcs.cibus.server.repository.PaymentRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@Service
public class PaymentService
{
    @Autowired
    private PaymentRepository paymentRepository;

    public Page<Payment> getAll(int pagina, int qtdElementos, String payment, String description, DomainActive active) throws Exception
    {
        Pageable paginacao = PageRequest.of(pagina, qtdElementos);

        payment = payment.isEmpty() ? null : payment.toLowerCase(Locale.ROOT);
        description = description.isEmpty() ? null : description.toLowerCase(Locale.ROOT);

        if (active.equals(DomainActive.BOUTH))
        {
            return paymentRepository.findAll(paginacao, payment, description);
        }
        else
        {
            boolean categoryActive = active.equals(DomainActive.YES) ? true : false;
            return paymentRepository.findAll(paginacao, payment, description, categoryActive);
        }
    }

    public List<Payment> getAllVisible() throws Exception
    {
        return paymentRepository.findAllVisible();
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
