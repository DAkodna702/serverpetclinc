package com.serverpet.server.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.serverpet.server.Models.MascotEntity;
import com.serverpet.server.Models.UserEntity;
import com.serverpet.server.Repositories.MascotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class MascotService {

    @Autowired
    private MascotRepository mascotRepository;

    // Crear o actualizar mascota
    public MascotEntity saveOrUpdateMascot(MascotEntity mascot) {
        return mascotRepository.save(mascot);
    }

    // Obtener todas las mascotas (sin borrado lógico)
    public List<MascotEntity> getAllMascots() {
        return mascotRepository.findAllMascots();
    }

    // Obtener mascota por ID (lanzar excepción si no se encuentra)
    public MascotEntity getMascotById(Long id) {
        return mascotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + id));
    }

    // Obtener mascotas por usuario específico
    public List<MascotEntity> getMascotsByUser(UserEntity user) {
        return mascotRepository.findAllByUser(user);
    }

    // Buscar mascotas por nombre exacto
    public List<MascotEntity> getMascotsByName(String name) {
        return mascotRepository.findByName(name);
    }

    // Eliminar mascota (borrado lógico)
    @Transactional
    public void deleteMascot(Long id) {
        if (mascotRepository.existsById(id)) {
            mascotRepository.softDelete(id);
        } else {
            throw new RuntimeException("Mascota no encontrada con ID: " + id);
        }
    }

}
