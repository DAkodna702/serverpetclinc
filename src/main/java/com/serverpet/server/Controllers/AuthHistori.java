package com.serverpet.server.Controllers;


import com.serverpet.server.Models.HistoriEntity;
import com.serverpet.server.Services.HistoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historias")
@CrossOrigin("http://localhost:3000")
public class AuthHistori {

    @Autowired
    private HistoriService historiService;

    // Obtener historias por ID de mascota y estado pendiente
    @GetMapping("/mascota/{mascotId}/pendiente")
    public List<HistoriEntity> getHistoriasByMascotIdAndPendingState(@PathVariable Long mascotId) {
        return historiService.findByMascotIdAndPendingState(mascotId);
    }

    // Obtener historias por ID de mascota ordenadas por estado
    @GetMapping("/mascota/{mascotId}/ordenadas")
    public List<HistoriEntity> getHistoriasByMascotIdOrderByState(@PathVariable Long mascotId) {
        return historiService.findByMascotIdOrderByState(mascotId);
    }

    // Obtener historias por ID de trabajador y estado pendiente
    @GetMapping("/trabajador/{workerId}/pendiente")
    public List<HistoriEntity> getHistoriasByWorkerIdAndPendingState(@PathVariable Long workerId) {
        return historiService.findByWorkerIdAndPendingState(workerId);
    }

    // Obtener historias por ID de trabajador ordenadas por estado
    @GetMapping("/trabajador/{workerId}/ordenadas")
    public List<HistoriEntity> getHistoriasByWorkerIdOrderByState(@PathVariable Long workerId) {
        return historiService.findByWorkerIdOrderByState(workerId);
    }

    // Crear una nueva historia clínica
    @PostMapping("/crear")
    public HistoriEntity createHistori(@RequestBody HistoriEntity historiEntity) {
        return historiService.createHistori(historiEntity);
    }

}
