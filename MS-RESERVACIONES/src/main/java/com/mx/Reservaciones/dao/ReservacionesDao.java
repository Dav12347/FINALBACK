package com.mx.Reservaciones.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.Reservaciones.dominio.Reservaciones;

public interface ReservacionesDao extends JpaRepository<Reservaciones, Integer> {

}
