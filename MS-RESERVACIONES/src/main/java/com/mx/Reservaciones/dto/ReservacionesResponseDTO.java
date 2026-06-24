package com.mx.Reservaciones.dto;

import java.time.LocalDateTime;

import com.mx.Reservaciones.dominio.EstadoReservacion;

import lombok.Data;

@Data
public class ReservacionesResponseDTO {

    private Integer idReservaciones;
    private Integer huespedId;
    private Integer habitaciondId;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Double totalCalculado;
    private EstadoReservacion estado;
    private LocalDateTime fechaCreacion;
}