package com.tadeo.mantenimiento_vehiculos.controller;

import com.tadeo.mantenimiento_vehiculos.dto.MantenimientoRequestDTO;
import com.tadeo.mantenimiento_vehiculos.dto.MantenimientoResponseDTO;
import com.tadeo.mantenimiento_vehiculos.service.MantenimientoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST que expone los endpoints para la gestión de mantenimientos.
 */
@RestController
@RequestMapping("/api/mantenimientos")
public class MantenimientoController {

    private final MantenimientoService mantenimientoService;

    public MantenimientoController(MantenimientoService mantenimientoService) {
        this.mantenimientoService = mantenimientoService;
    }

    /**
     * Obtiene todos los mantenimientos del sistema.
     * @return Lista de mantenimientos.
     */
    @GetMapping
    public List<MantenimientoResponseDTO> obtenerTodos() {
        return mantenimientoService.obtenerTodos();
    }

    /**
     * Obtiene un mantenimiento por su ID.
     * @param id ID del mantenimiento.
     * @return 200 OK con el mantenimiento, o 404 Not Found si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MantenimientoResponseDTO> obtenerPorId(@PathVariable Long id) {
        Optional<MantenimientoResponseDTO> mantenimiento = mantenimientoService.obtenerPorId(id);
        return mantenimiento.map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Registra un nuevo mantenimiento en el sistema.
     * @param requestDTO Datos validados del mantenimiento.
     * @return 200 OK con el mantenimiento creado.
     */
    @PostMapping
    public ResponseEntity<MantenimientoResponseDTO> crearMantenimiento(@Valid @RequestBody MantenimientoRequestDTO requestDTO) {
        MantenimientoResponseDTO creado = mantenimientoService.guardar(requestDTO);
        return ResponseEntity.ok(creado);
    }

    /**
     * Elimina un mantenimiento por su ID.
     * @param id ID del mantenimiento a eliminar.
     * @return 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMantenimiento(@PathVariable Long id) {
        mantenimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
