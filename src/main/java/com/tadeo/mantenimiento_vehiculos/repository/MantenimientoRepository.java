package com.tadeo.mantenimiento_vehiculos.repository;

import com.tadeo.mantenimiento_vehiculos.model.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de acceso a datos para la entidad Mantenimiento.
 */
@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimiento, Long> {
}
