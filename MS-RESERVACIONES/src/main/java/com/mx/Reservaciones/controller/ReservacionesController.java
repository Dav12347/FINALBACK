package com.mx.Reservaciones.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mx.Reservaciones.dominio.EstadoReservacion;
import com.mx.Reservaciones.dominio.Reservaciones;
import com.mx.Reservaciones.service.ReservacionesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reservaciones")
@RequiredArgsConstructor
@CrossOrigin
public class ReservacionesController {

    private final ReservacionesService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservaciones guardar(
            @RequestBody Reservaciones reservacion) {

        return service.guardar(reservacion);
    }

    @GetMapping
    public List<Reservaciones> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Reservaciones buscarPorId(
            @PathVariable("id") Integer id) {

        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Reservaciones actualizar(
            @PathVariable("id") Integer id,
            @RequestBody Reservaciones reservacion) {

        return service.actualizar(id, reservacion);
    }

    @PatchMapping("/{id}/estado")
    public Reservaciones cambiarEstado(
            @PathVariable("id") Integer id,
            @RequestParam EstadoReservacion estado) {

        return service.cambiarEstado(id, estado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(
            @PathVariable("id") Integer id) {

        service.eliminar(id);
    }
}