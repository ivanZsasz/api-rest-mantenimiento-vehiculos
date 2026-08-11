package com.tadeo.mantenimiento_vehiculos.service;

import com.tadeo.mantenimiento_vehiculos.dto.VehiculoRequestDTO;
import com.tadeo.mantenimiento_vehiculos.dto.VehiculoResponseDTO;
import com.tadeo.mantenimiento_vehiculos.model.Vehiculo;
import com.tadeo.mantenimiento_vehiculos.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona la lógica de negocio referente a los vehículos.
 */
@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    /**
     * Recupera el catálogo completo de vehículos registrados.
     * @return Lista de vehículos en formato DTO.
     */
    public List<VehiculoResponseDTO> obtenerTodos() {
        return vehiculoRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un vehículo específico por su identificador.
     * @param id Identificador único del vehículo.
     * @return Optional con el vehículo si existe, o vacío.
     */
    public Optional<VehiculoResponseDTO> obtenerPorId(Long id) {
        return vehiculoRepository.findById(id)
                .map(this::convertirAResponseDTO);
    }

    /**
     * Persiste un nuevo vehículo en el sistema.
     * @param requestDTO Datos del vehículo a crear.
     * @return El vehículo creado en formato DTO.
     */
    public VehiculoResponseDTO guardar(VehiculoRequestDTO requestDTO) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMarca(requestDTO.getMarca());
        vehiculo.setModelo(requestDTO.getModelo());
        vehiculo.setAnio(requestDTO.getAnio());
        vehiculo.setKilometraje(requestDTO.getKilometraje());

        Vehiculo guardado = vehiculoRepository.save(vehiculo);
        
        return convertirAResponseDTO(guardado);
    }

    /**
     * Elimina un vehículo existente del sistema.
     * @param id Identificador único del vehículo.
     */
    public void eliminar(Long id) {
        vehiculoRepository.deleteById(id);
    }

    private VehiculoResponseDTO convertirAResponseDTO(Vehiculo vehiculo) {
        VehiculoResponseDTO responseDTO = new VehiculoResponseDTO();
        responseDTO.setId(vehiculo.getId());
        responseDTO.setMarca(vehiculo.getMarca());
        responseDTO.setModelo(vehiculo.getModelo());
        responseDTO.setAnio(vehiculo.getAnio());
        responseDTO.setKilometraje(vehiculo.getKilometraje());
        return responseDTO;
    }
}
