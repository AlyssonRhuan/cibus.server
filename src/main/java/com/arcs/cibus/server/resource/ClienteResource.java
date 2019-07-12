package com.arcs.cibus.server.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arcs.cibus.server.domain.Cliente;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.service.ClienteService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Cliente> listar() {
		return null;
	}
	
	@RequestMapping(value="/{clienteId}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Long clienteId) throws ObjectNotFoundException {
		Cliente cliente = clienteService.findById(clienteId);
		return ResponseEntity.ok(cliente);
	}
}
	