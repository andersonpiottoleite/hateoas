package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente save(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		cliente.setNome(clienteDTO.getNome());
		return clienteRepository.save(cliente);
	}
	
	public Cliente getById(int id) {
		return clienteRepository.findById(Long.valueOf(id)).get();
	}

	public List<Cliente> getAll() {
		return clienteRepository.findAll();
	}

}
