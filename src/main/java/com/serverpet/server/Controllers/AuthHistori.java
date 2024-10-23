package com.serverpet.server.Controllers;


import com.serverpet.server.DTO.HistoriDTORequest;
import com.serverpet.server.DTO.HistoriDTOResponse;
import com.serverpet.server.Models.HistoriEntity;
import com.serverpet.server.Models.MascotEntity;
import com.serverpet.server.Models.WorkerEntity;
import com.serverpet.server.Repositories.MascotRepository;
import com.serverpet.server.Repositories.WorkerRepository;
import com.serverpet.server.Services.HistoriService;
import com.serverpet.server.Util.HistoriState;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/hist")
public class AuthHistori {

    @Autowired
    private HistoriService historiService;

    @Autowired
    private MascotRepository mascotRepository;

    @Autowired
    private WorkerRepository workerRepository;


    @GetMapping("/mascotP/{mascotId}")
    public List<HistoriDTOResponse> getHistoriasByMascotIdAndPendingState(@PathVariable Long mascotId) {
        return historiService.findByMascotIdAndPendingState(mascotId);
    }

    @GetMapping("/mascotT/{mascotId}")
    public List<HistoriDTOResponse> getHistoriasByMascotIdOrderByState(@PathVariable Long mascotId) {
        return historiService.findByMascotIdOrderByState(mascotId);
    }

    @GetMapping("/workerP/{workerId}")
    public List<HistoriDTOResponse> getHistoriasByWorkerIdAndPendingState(@PathVariable Long workerId) {
        return historiService.findByWorkerIdAndPendingState(workerId);
    }

    @GetMapping("/workerT/{workerId}")
    public List<HistoriDTOResponse> getHistoriasByWorkerIdOrderByState(@PathVariable Long workerId) {
        return historiService.findByWorkerIdOrderByState(workerId);
    }

    /*----------------------------------------------------*/
    @PostMapping("/save")
    public ResponseEntity<HistoriEntity> createHistori(@RequestBody @Valid HistoriDTORequest historiDTORequest) {
        // Buscar entidades relacionadas usando los IDs proporcionados
        MascotEntity mascot = mascotRepository.findById(historiDTORequest.getMascotId())
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        WorkerEntity worker = workerRepository.findById(historiDTORequest.getWorkerId())
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));

        // Crear nueva entidad de Histori
        HistoriEntity historiEntity = new HistoriEntity();
        historiEntity.setFecha(historiDTORequest.getFecha());
        historiEntity.setMotivo(historiDTORequest.getMotivo());
        historiEntity.setPrioridad(historiDTORequest.getPrioridad());
        historiEntity.setDiagnostico(historiDTORequest.getDiagnostico());
        historiEntity.setTratamiento(historiDTORequest.getTratamiento());
        historiEntity.setHistoristate(historiDTORequest.getHistoristate());
        historiEntity.setMascotentity(mascot);
        historiEntity.setWorkerEntity(worker);

        // Guardar la nueva cita
        HistoriEntity savedHistori = historiService.createHistori(historiEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHistori);
    }


    /*---------------------------------------------*/
    // Obtener todas las citas
    @GetMapping("/all")
    public List<HistoriDTOResponse> getAllHistorias() {
        return historiService.findAll();
    }

    // Actualizar el estado de una cita específica
    @PutMapping("/updateC/{id}")
    public ResponseEntity<HistoriDTOResponse> updateHistori(
            @PathVariable Long id,
            @RequestBody @Valid HistoriDTORequest historiDTORequest) {
        HistoriDTOResponse updatedHistori = historiService.updateHistori(id, historiDTORequest);
        return ResponseEntity.ok(updatedHistori);
    }
}

