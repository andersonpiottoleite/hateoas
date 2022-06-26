package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.RootEntryPointModel;

@RestController
@RequestMapping("/")
public class RootEntryPointController {
	

    @GetMapping
    public ResponseEntity<RootEntryPointModel> getRoot() {
        RootEntryPointModel resp = new RootEntryPointModel();
        resp.add(linkTo(methodOn(ClienteController.class).save(null)).withRel("cliente") );
        return ResponseEntity.ok(resp);
    }

}
