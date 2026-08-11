package com.tadeo.mantenimiento_vehiculos.dto;

import java.time.LocalDate;

/**
 * Objeto de transferencia de datos utilizado para devolver la información de un mantenimiento al cliente.
 */
public class MantenimientoResponseDTO {
    private Long id;
    private String descripcion;
    private Double costo;
    private LocalDate fecha;
    private Long vehiculoId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }
}
