package com.serverpet.server.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.serverpet.server.DTO.MascotDTO;
import com.serverpet.server.DTO.MascotDTORequest;
import com.serverpet.server.DTO.MascotDTORseponse;
import com.serverpet.server.Models.MascotEntity;
import com.serverpet.server.Models.UserEntity;
import com.serverpet.server.Repositories.MascotRepository;
import com.serverpet.server.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class MascotService {

    @Autowired
    private MascotRepository mascotRepository;


    public MascotEntity saveOrUpdateMascot(MascotEntity mascot) {
        return mascotRepository.save(mascot);
    }

    // Obtener todas las mascotas activas
    public List<MascotDTORseponse> getAllMascots() {
        List<MascotEntity> mascots = mascotRepository.findAllActiveMascots();


        return mascots.stream().map(mascot -> new MascotDTORseponse(
                mascot.getId(),
                mascot.getName(),
                mascot.getYears_old(),
                mascot.getRace(),
                mascot.getSex(),
                mascot.isDeleted(),
                mascot.getUserentidad().getUsername()
        )).collect(Collectors.toList());
    }


    public MascotEntity getMascotById(Long id) {
        return mascotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada con ID: " + id));
    }


    public List<MascotDTORseponse> getMascotsByUser(String username) {
        List<MascotEntity> mascots = mascotRepository.findAllByUserUsername(username);
        return mascots.stream().map(mascot -> new MascotDTORseponse(
                mascot.getId(),
                mascot.getName(),
                mascot.getYears_old(),
                mascot.getRace(),
                mascot.getSex(),
                mascot.isDeleted(),
                mascot.getUserentidad().getUsername()
        )).collect(Collectors.toList());
    }


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

    public MascotEntity createMascot(MascotDTO mascotDTO, UserRepository userRepository) {
        UserEntity user = userRepository.findById(mascotDTO.getUserentidadId())
                .orElseThrow();

        MascotEntity mascot = MascotEntity.builder()
                .name(mascotDTO.getName())
                .years_old(mascotDTO.getYears_old())
                .race(mascotDTO.getRace())
                .sex(mascotDTO.getSex())
                .isDeleted(mascotDTO.isDeleted())
                .userentidad(user)
                .build();

        return saveOrUpdateMascot(mascot);
    }

    public MascotEntity updateMascot(Long id, MascotDTORequest mascotDTORequest) {
        MascotEntity existingMascot = getMascotById(id);
        existingMascot.setName(mascotDTORequest.getName());
        existingMascot.setYears_old(mascotDTORequest.getYears_old());
        existingMascot.setRace(mascotDTORequest.getRace());
        existingMascot.setSex(mascotDTORequest.getSex());

        return saveOrUpdateMascot(existingMascot);
    }

}
