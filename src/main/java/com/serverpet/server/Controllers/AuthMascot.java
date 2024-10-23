package com.serverpet.server.Controllers;


import com.serverpet.server.DTO.MascotDTO;
import com.serverpet.server.DTO.MascotDTORequest;
import com.serverpet.server.DTO.MascotDTORseponse;
import com.serverpet.server.Models.MascotEntity;
import com.serverpet.server.Models.UserEntity;
import com.serverpet.server.Repositories.UserRepository;
import com.serverpet.server.Services.MascotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/mascot")
public class AuthMascot {

    @Autowired
    private MascotService mascotService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/user/{username}")
    public ResponseEntity<List<MascotDTORseponse>> getMascotsByUser(@PathVariable String username) {
        // Obtenemos las mascotas y devolvemos la lista de DTOs
        List<MascotDTORseponse> mascots = mascotService.getMascotsByUser(username);
        return new ResponseEntity<>(mascots, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<MascotEntity> createMascot(@RequestBody @Valid MascotDTO mascotDTO) {
        MascotEntity savedMascot = mascotService.createMascot(mascotDTO, userRepository);
        return new ResponseEntity<>(savedMascot, HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<MascotDTO> updateMascot(@PathVariable Long id, @RequestBody @Valid MascotDTORequest mascotDTORequest) {
        MascotEntity updatedMascot = mascotService.updateMascot(id, mascotDTORequest);
        MascotDTO response = new MascotDTO(
                updatedMascot.getName(),
                updatedMascot.getYears_old(),
                updatedMascot.getRace(),
                updatedMascot.getSex(),
                updatedMascot.isDeleted(),
                updatedMascot.getUserentidad().getId()
        );
        return ResponseEntity.ok(response);
    }

    //++++++++++++++++++++++++++++++++++++++++++
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMascot(@PathVariable Long id) {
        // Verificar si la mascota existe antes de eliminar
        MascotEntity existingMascot = mascotService.getMascotById(id);

        // Realizar el borrado lógico
        mascotService.deleteMascot(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    //+++++++++++++++++++++++++++++++++++++++++++++
    @GetMapping("/listmascot")
    public ResponseEntity<List<MascotDTORseponse>> getAllActiveMascots() {
        List<MascotDTORseponse> activeMascots = mascotService.getAllMascots();
        return ResponseEntity.ok(activeMascots);
    }

}
