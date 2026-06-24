package com.mx.Reservaciones.dominio;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "RESERVACIONES-MS")
@Data
public class Reservaciones {

		@Id
		private Integer idReservaciones;
		private Integer huespedId;
		private Integer habitaciondId;
		private LocalDateTime fechaInicio;
		private LocalDateTime fechaFin;
	    private Double totalCalculado;
	    
	    @Enumerated(EnumType.STRING)
	    private EstadoReservacion estado;
	    
	    private LocalDateTime fechaCreacion;


		
}
