package com.serverpet.server.Controllers;


import com.serverpet.server.Models.MascotEntity;
import com.serverpet.server.Models.UserEntity;
import com.serverpet.server.Repositories.UserRepository;
import com.serverpet.server.Services.MascotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mascot")
@CrossOrigin("http://localhost:3000")
public class AuthMascot {

    @Autowired
    private MascotService mascotService;

    @Autowired
    private UserRepository userRepository;

    // Obtener todas las mascotas de un usuario
    @GetMapping("/user/{username}")
    public ResponseEntity<List<MascotEntity>> getMascotsByUser(@PathVariable String username) {
        // Aquí deberías obtener la entidad del usuario por su nombre de usuario
        UserEntity user = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con nombre: " + username));

        List<MascotEntity> mascots = mascotService.getMascotsByUser(user);
        return ResponseEntity.ok(mascots);
    }

    @PostMapping("/create")
    public ResponseEntity<MascotEntity> createMascot(@RequestBody MascotEntity mascot) {
        MascotEntity savedMascot = mascotService.saveOrUpdateMascot(mascot);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMascot);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MascotEntity> updateMascot(@PathVariable Long id, @RequestBody MascotEntity mascotDetails) {
        // Buscar la mascota existente y lanzar excepción si no se encuentra
        MascotEntity existingMascot = mascotService.getMascotById(id);

        // Actualizar los datos de la mascota
        existingMascot.setName(mascotDetails.getName());
        existingMascot.setYears_old(mascotDetails.getYears_old());
        existingMascot.setRace(mascotDetails.getRace());
        existingMascot.setSex(mascotDetails.getSex());

        // Guardar la mascota actualizada
        MascotEntity updatedMascot = mascotService.saveOrUpdateMascot(existingMascot);
        return ResponseEntity.ok(updatedMascot);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMascot(@PathVariable Long id) {
        // Verificar si la mascota existe antes de eliminar
        MascotEntity existingMascot = mascotService.getMascotById(id);

        // Realizar el borrado lógico
        mascotService.deleteMascot(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @GetMapping("/listmascot")
    public ResponseEntity<List<MascotEntity>> getAllActiveMascots() {
        List<MascotEntity> activeMascots = mascotService.getAllMascots();
        return ResponseEntity.ok(activeMascots);
    }

}
