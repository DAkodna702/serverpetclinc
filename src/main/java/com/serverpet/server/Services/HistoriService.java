package com.serverpet.server.Services;

import java.util.List;

import com.serverpet.server.Repositories.HistoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serverpet.server.Models.HistoriEntity;


@Service
public class HistoriService {

    @Autowired
    private HistoriRepository historiRepository;

    // Método para obtener historias por mascota y estado pendiente
    public List<HistoriEntity> findByMascotIdAndPendingState(Long mascotId) {
        return historiRepository.findByMascotIdAndPendingState(mascotId);
    }

    // Método para obtener historias por mascota ordenadas por estado
    public List<HistoriEntity> findByMascotIdOrderByState(Long mascotId) {
        return historiRepository.findByMascotIdOrderByState(mascotId);
    }

    // Método para obtener historias por trabajador y estado pendiente
    public List<HistoriEntity> findByWorkerIdAndPendingState(Long workerId) {
        return historiRepository.findByWorkerIdAndPendingState(workerId);
    }

    // Método para obtener historias por trabajador ordenadas por estado
    public List<HistoriEntity> findByWorkerIdOrderByState(Long workerId) {
        return historiRepository.findByWorkerIdOrderByState(workerId);
    }

    // Método para crear una nueva historia clínica
    public HistoriEntity createHistori(HistoriEntity historiEntity) {
        return historiRepository.save(historiEntity);
    }

}
