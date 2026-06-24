package com.mx.Gateway.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.Gateway.dominio.Usuario;

public interface IUsuariosDao extends JpaRepository<Usuario, Integer> {
	
	Optional<Usuario> findByUsername(String usuername);

}
