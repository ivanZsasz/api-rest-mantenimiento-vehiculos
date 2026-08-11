package com.tadeo.mantenimiento_vehiculos.service;

import com.tadeo.mantenimiento_vehiculos.dto.MantenimientoRequestDTO;
import com.tadeo.mantenimiento_vehiculos.dto.MantenimientoResponseDTO;
import com.tadeo.mantenimiento_vehiculos.model.Mantenimiento;
import com.tadeo.mantenimiento_vehiculos.model.Vehiculo;
import com.tadeo.mantenimiento_vehiculos.repository.MantenimientoRepository;
import com.tadeo.mantenimiento_vehiculos.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona la lógica de negocio referente a los mantenimientos.
 */
@Service
public class MantenimientoService {

    private final MantenimientoRepository mantenimientoRepository;
    private final VehiculoRepository vehiculoRepository;

    public MantenimientoService(MantenimientoRepository mantenimientoRepository, VehiculoRepository vehiculoRepository) {
        this.mantenimientoRepository = mantenimientoRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    /**
     * Recupera todos los mantenimientos registrados.
     * @return Lista de mantenimientos en formato DTO.
     */
    public List<MantenimientoResponseDTO> obtenerTodos() {
        return mantenimientoRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un mantenimiento por su identificador.
     * @param id Identificador único del mantenimiento.
     * @return Optional con el mantenimiento si existe, o vacío.
     */
    public Optional<MantenimientoResponseDTO> obtenerPorId(Long id) {
        return mantenimientoRepository.findById(id)
                .map(this::convertirAResponseDTO);
    }

    /**
     * Persiste un nuevo mantenimiento asociándolo a un vehículo existente.
     * @param requestDTO Datos del mantenimiento.
     * @return El mantenimiento creado en formato DTO.
     * @throws IllegalArgumentException si el vehículo asociado no existe.
     */
    public MantenimientoResponseDTO guardar(MantenimientoRequestDTO requestDTO) {
        Vehiculo vehiculo = vehiculoRepository.findById(requestDTO.getVehiculoId())
                .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado con ID: " + requestDTO.getVehiculoId()));

        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setDescripcion(requestDTO.getDescripcion());
        mantenimiento.setCosto(requestDTO.getCosto());
        mantenimiento.setFecha(requestDTO.getFecha());
        mantenimiento.setVehiculo(vehiculo);

        Mantenimiento guardado = mantenimientoRepository.save(mantenimiento);
        
        return convertirAResponseDTO(guardado);
    }

    /**
     * Elimina un mantenimiento del sistema.
     * @param id Identificador único del mantenimiento.
     */
    public void eliminar(Long id) {
        mantenimientoRepository.deleteById(id);
    }

    private MantenimientoResponseDTO convertirAResponseDTO(Mantenimiento mantenimiento) {
        MantenimientoResponseDTO responseDTO = new MantenimientoResponseDTO();
        responseDTO.setId(mantenimiento.getId());
        responseDTO.setDescripcion(mantenimiento.getDescripcion());
        responseDTO.setCosto(mantenimiento.getCosto());
        responseDTO.setFecha(mantenimiento.getFecha());
        if (mantenimiento.getVehiculo() != null) {
            responseDTO.setVehiculoId(mantenimiento.getVehiculo().getId());
        }
        return responseDTO;
    }
}
