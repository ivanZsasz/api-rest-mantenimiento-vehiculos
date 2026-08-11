package com.tadeo.mantenimiento_vehiculos.controller;

import com.tadeo.mantenimiento_vehiculos.dto.VehiculoRequestDTO;
import com.tadeo.mantenimiento_vehiculos.dto.VehiculoResponseDTO;
import com.tadeo.mantenimiento_vehiculos.service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST que expone los endpoints para la gestión de vehículos.
 */
@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    /**
     * Obtiene todos los vehículos del sistema.
     * @return Lista de vehículos.
     */
    @GetMapping
    public List<VehiculoResponseDTO> obtenerTodos() {
        return vehiculoService.obtenerTodos();
    }

    /**
     * Obtiene un vehículo por su ID.
     * @param id ID del vehículo.
     * @return 200 OK con el vehículo, o 404 Not Found si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VehiculoResponseDTO> obtenerPorId(@PathVariable Long id) {
        Optional<VehiculoResponseDTO> vehiculo = vehiculoService.obtenerPorId(id);
        
        return vehiculo.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Registra un nuevo vehículo en el sistema.
     * @param requestDTO Datos validados del vehículo.
     * @return 200 OK con el vehículo creado.
     */
    @PostMapping
    public ResponseEntity<VehiculoResponseDTO> crearVehiculo(@Valid @RequestBody VehiculoRequestDTO requestDTO) {
        VehiculoResponseDTO creado = vehiculoService.guardar(requestDTO);
        return ResponseEntity.ok(creado);
    }

    /**
     * Elimina un vehículo por su ID.
     * @param id ID del vehículo a eliminar.
     * @return 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVehiculo(@PathVariable Long id) {
        vehiculoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
