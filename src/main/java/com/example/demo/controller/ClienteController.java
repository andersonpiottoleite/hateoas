package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping
	public ResponseEntity<ClienteDTO> save(@Valid @RequestBody ClienteDTO cliente) {
		Cliente clienteSalvo = clienteService.save(cliente);
		
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setId(clienteSalvo.getId());
		clienteDTO.setNome(clienteSalvo.getNome());

		if (Objects.nonNull(clienteSalvo)) {

			// linkTo(ClienteController.class) .slash(clienteSalvo.getId()) .withSelfRel();

			clienteDTO.add(linkTo(methodOn(ClienteController.class).getById(String.valueOf(clienteSalvo.getId())))
					.withSelfRel());

			clienteDTO.add(linkTo(methodOn(ClienteController.class).getAll()).withRel(IanaLinkRelations.COLLECTION));

			return ResponseEntity.ok(clienteDTO);

		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> getById(@PathVariable String id) {
		Cliente cliente = clienteService.getById(Integer.parseInt(id));
		
		if(cliente.getClass().isInstance(Cliente.class)) {
			System.out.println("OK");
		}
		
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setId(cliente.getId());
		clienteDTO.setNome(cliente.getNome());


		if (Objects.nonNull(clienteDTO)) {
			return ResponseEntity.ok(clienteDTO);

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/all")
	public ResponseEntity<CollectionModel<ClienteDTO>> getAll() {
		List<Cliente> clientes = clienteService.getAll();
		
		List<ClienteDTO> clientesDTO = getClienteDTO(clientes);

		if (!clientesDTO.isEmpty()) {
			for (ClienteDTO cliente : clientesDTO) {
				if (cliente.getLinks().isEmpty()) {
					cliente.add(linkTo(methodOn(ClienteController.class).getById(String.valueOf(cliente.getId())))
							.withSelfRel());
					
					cliente.add(linkTo(methodOn(ClienteController.class).getAll()).withRel(IanaLinkRelations.COLLECTION));				
				}
				
			}
			
			CollectionModel<ClienteDTO> collectionModel = CollectionModel.of(clientesDTO);
			
			collectionModel.add(linkTo(methodOn(ClienteController.class).getAll()).withSelfRel());
			
			return ResponseEntity.ok(collectionModel);
			
		} else {
			return ResponseEntity.noContent().build();
		} 

	}

	private List<ClienteDTO> getClienteDTO(List<Cliente> clientes) {
		List<ClienteDTO> clientesDTO = clientes.stream().map( c -> {
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setId(c.getId());
			clienteDTO.setNome(c.getNome());
			return clienteDTO;
		}).collect(Collectors.toList());
		return clientesDTO;
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Cliente cliente = new Cliente();
		if(cliente.getClass().isInstance(new Cliente())) {
			System.out.println("OK");
		}else {
			System.out.println("NOK");
		}
		
		if(cliente instanceof Cliente) {
			System.out.println("OK");
		}else {
			System.out.println("NOK");
		}
		
	}
}
