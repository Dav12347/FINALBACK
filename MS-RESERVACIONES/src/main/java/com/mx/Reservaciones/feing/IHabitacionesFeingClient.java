package com.mx.Reservaciones.feing;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mx.Reservaciones.dto.HabitacionesDTO;

public interface IHabitacionesFeingClient {

	@FeignClient(name = "MS-HABITACIONES")
	public interface IHabitacionesFeignClient {

	    @GetMapping("/habitaciones/{id}")
	    HabitacionesDTO buscarPorId(@PathVariable Integer id);
	}
}
