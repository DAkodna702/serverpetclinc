package com.serverpet.server.Services;

import java.util.List;
import java.util.stream.Collectors;

import com.serverpet.server.DTO.HistoriDTORequest;
import com.serverpet.server.DTO.HistoriDTOResponse;
import com.serverpet.server.Models.MascotEntity;
import com.serverpet.server.Models.WorkerEntity;
import com.serverpet.server.Repositories.HistoriRepository;
import com.serverpet.server.Repositories.MascotRepository;
import com.serverpet.server.Repositories.WorkerRepository;
import com.serverpet.server.Util.HistoriState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serverpet.server.Models.HistoriEntity;


@Service
public class HistoriService {

    @Autowired
    private HistoriRepository historiRepository;

    @Autowired
    private MascotRepository mascotRepository;
    @Autowired
    private WorkerRepository workerRepository;

    // Listar todas las citas
    public List<HistoriDTOResponse> findAll() {
        List<HistoriEntity> entities = historiRepository.findAll();
        return mapToDTOs(entities);
    }

    // Actualizar solo el estado de una cita específica
    public HistoriDTOResponse updateHistori(Long id, HistoriDTORequest historiDTORequest) {
        // Buscar la historia existente por ID
        HistoriEntity histori = historiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        // Actualizar los campos de la entidad
        histori.setFecha(historiDTORequest.getFecha());
        histori.setMotivo(historiDTORequest.getMotivo());
        histori.setPrioridad(historiDTORequest.getPrioridad());
        histori.setDiagnostico(historiDTORequest.getDiagnostico());
        histori.setTratamiento(historiDTORequest.getTratamiento());
        histori.setHistoristate(historiDTORequest.getHistoristate());

        // Buscar y asignar las entidades relacionadas (mascota y trabajador)
        MascotEntity mascot = mascotRepository.findById(historiDTORequest.getMascotId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
        WorkerEntity worker = workerRepository.findById(historiDTORequest.getWorkerId())
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));

        histori.setMascotentity(mascot);
        histori.setWorkerEntity(worker);

        // Guardar la historia actualizada
        HistoriEntity updatedHistori = historiRepository.save(histori);

        // Devolver el DTO actualizado
        return mapToDTO(updatedHistori);
    }


    public List<HistoriDTOResponse> findByMascotIdAndPendingState(Long mascotId) {
        List<HistoriEntity> entities = historiRepository.findByMascotIdAndPendingState(mascotId);
        return mapToDTOs(entities);
    }

    public List<HistoriDTOResponse> findByMascotIdOrderByState(Long mascotId) {
        List<HistoriEntity> entities = historiRepository.findByMascotIdOrderByState(mascotId);
        return mapToDTOs(entities);
    }

    public List<HistoriDTOResponse> findByWorkerIdAndPendingState(Long workerId) {
        List<HistoriEntity> entities = historiRepository.findByWorkerIdAndPendingState(workerId);
        return mapToDTOs(entities);
    }

    public List<HistoriDTOResponse> findByWorkerIdOrderByState(Long workerId) {
        List<HistoriEntity> entities = historiRepository.findByWorkerIdOrderByState(workerId);
        return mapToDTOs(entities);
    }

    public HistoriEntity createHistori(HistoriEntity historiEntity) {
        return historiRepository.save(historiEntity);
    }

    private List<HistoriDTOResponse> mapToDTOs(List<HistoriEntity> entities) {
        return entities.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private HistoriDTOResponse mapToDTO(HistoriEntity entity) {
        return new HistoriDTOResponse(
                entity.getId(),
                entity.getFecha(),
                entity.getMotivo(),
                entity.getPrioridad(),
                entity.getDiagnostico(),
                entity.getTratamiento(),
                entity.getHistoristate(),
                entity.getMascotentity().getName(), // Nombre de la mascota
                entity.getWorkerEntity().getUsername(), // Nombre del trabajador
                entity.getWorkerEntity().getRole().name() // Rol del trabajador
        );
    }

}
