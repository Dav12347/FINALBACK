package com.mx.Gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Gateway.dominio.Usuario;
import com.mx.Gateway.service.UsuariosService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("usuarios")
@CrossOrigin
public class UsuariosController {
	
	private final UsuariosService service;
	
	@PostMapping("registro")
	public ResponseEntity<?> registrar(@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(usuario));
	}

}
