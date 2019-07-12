package com.arcs.cibus.server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.Cliente;
import com.arcs.cibus.server.repository.ClienteRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente findById(Long clienteId) throws ObjectNotFoundException {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);		
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + clienteId + " não existe."));
	}
}
