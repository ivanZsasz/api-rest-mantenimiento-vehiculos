package com.tadeo.mantenimiento_vehiculos.model;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidad de dominio que representa un registro de mantenimiento.
 * Mantiene una relación de pertenencia (ManyToOne) con un Vehículo específico.
 */
@Entity
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private Double costo;
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    public Mantenimiento() {}

    public Mantenimiento(String descripcion, Double costo, LocalDate fecha, Vehiculo vehiculo) {
        this.descripcion = descripcion;
        this.costo = costo;
        this.fecha = fecha;
        this.vehiculo = vehiculo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public Double getCosto() { return costo; }
    public void setCosto(Double costo) { this.costo = costo; }
    
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    
    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }
}
