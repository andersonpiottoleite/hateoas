package com.example.demo.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "clientes")
public class ClienteDTO extends RepresentationModel<ClienteDTO> {

	private Long id;
	
	@NotNull
	@NotEmpty
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
