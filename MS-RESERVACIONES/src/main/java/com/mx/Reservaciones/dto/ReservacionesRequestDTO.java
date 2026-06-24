package com.mx.Reservaciones.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservacionesRequestDTO {

    @NotNull(message = "El id del huésped es obligatorio")
    private Integer huespedId;

    @NotNull(message = "El id de la habitación es obligatorio")
    private Integer habitaciondId;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDateTime fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    @Future(message = "La fecha de fin debe ser futura")
    private LocalDateTime fechaFin;
}