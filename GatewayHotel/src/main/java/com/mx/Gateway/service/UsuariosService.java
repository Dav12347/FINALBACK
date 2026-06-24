package com.mx.Gateway.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mx.Gateway.dao.IUsuariosDao;
import com.mx.Gateway.dominio.Rol;
import com.mx.Gateway.dominio.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuariosService {
	
	private final IUsuariosDao dao;
	private final PasswordEncoder encoder;
	
	public Usuario registrar(Usuario usuario) {
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		
		if(usuario.getRole() == null) {
			usuario.setRole(Rol.USER);
	}
	return dao.save(usuario);

	}
}