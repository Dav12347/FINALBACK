package com.mx.Reservaciones.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mx.Reservaciones.dao.ReservacionesDao;
import com.mx.Reservaciones.dominio.EstadoReservacion;
import com.mx.Reservaciones.dominio.Reservaciones;
import com.mx.Reservaciones.dto.HabitacionesDTO;
import com.mx.Reservaciones.exception.InvalidReservacionStateException;
import com.mx.Reservaciones.exception.ReservacionNotFoundException;
import com.mx.Reservaciones.feing.IHabitacionesFeingClient.IHabitacionesFeignClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservacionesService {

    private final ReservacionesDao dao;
    private final IHabitacionesFeignClient habitacionesFeign;

    // Guardar
    public Reservaciones guardar(Reservaciones reservacion) {

        HabitacionesDTO habitacion =
                habitacionesFeign.buscarPorId(
                        reservacion.getHabitaciondId());

        long dias = ChronoUnit.DAYS.between(
                reservacion.getFechaInicio(),
                reservacion.getFechaFin());

        if (dias <= 0) {
            dias = 1;
        }

        reservacion.setTotalCalculado(
                habitacion.getPrecioPorNoche() * dias);

        reservacion.setEstado(
                EstadoReservacion.PENDIENTE);

        reservacion.setFechaCreacion(
                LocalDateTime.now());

        return dao.save(reservacion);
    }

    // Listar
    public List<Reservaciones> listar() {
        return dao.findAll();
    }

    // Buscar
    public Reservaciones buscarPorId(Integer id) {

        return dao.findById(id)
                .orElseThrow(() ->
                        new ReservacionNotFoundException(
                                "Reservación no encontrada"));
    }

    // Actualizar
    public Reservaciones actualizar(
            Integer id,
            Reservaciones datos) {

        Reservaciones reservacion =
                buscarPorId(id);

        reservacion.setFechaInicio(
                datos.getFechaInicio());

        reservacion.setFechaFin(
                datos.getFechaFin());

        return dao.save(reservacion);
    }

    // Eliminar
    public void eliminar(Integer id) {

        Reservaciones reservacion =
                buscarPorId(id);

        dao.delete(reservacion);
    }

    // Máquina de estados
    public Reservaciones cambiarEstado(
            Integer id,
            EstadoReservacion nuevoEstado) {

        Reservaciones reservacion =
                buscarPorId(id);

        validarTransicion(
                reservacion.getEstado(),
                nuevoEstado);

        reservacion.setEstado(nuevoEstado);

        return dao.save(reservacion);
    }

    private void validarTransicion(
            EstadoReservacion actual,
            EstadoReservacion nuevo) {

        switch (actual) {

            case PENDIENTE:

                if (nuevo != EstadoReservacion.CONFIRMADA &&
                    nuevo != EstadoReservacion.CANCELADA) {

                    throw new InvalidReservacionStateException(
                            "No se puede pasar de "
                            + actual + " a " + nuevo);
                }
                break;

            case CONFIRMADA:

                if (nuevo != EstadoReservacion.EN_CURSO &&
                    nuevo != EstadoReservacion.CANCELADA) {

                    throw new InvalidReservacionStateException(
                            "No se puede pasar de "
                            + actual + " a " + nuevo);
                }
                break;

            case EN_CURSO:

                if (nuevo != EstadoReservacion.FINALIZADA) {

                    throw new InvalidReservacionStateException(
                            "No se puede pasar de "
                            + actual + " a " + nuevo);
                }
                break;

            case FINALIZADA:
            case CANCELADA:

                throw new InvalidReservacionStateException(
                        "La reservación ya no admite cambios");
        }
    }
}