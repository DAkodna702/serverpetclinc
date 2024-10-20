package com.serverpet.server.Controllers;

import com.serverpet.server.DTO.UserListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.serverpet.server.DTO.AuthCreateUserRequest;
import com.serverpet.server.DTO.AuthLoginRequest;
import com.serverpet.server.DTO.AuthResponse;
import com.serverpet.server.Services.WorkerService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping ("/auth2/worker")
public class AuthWorker {

    
    @Autowired
    private WorkerService workerService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest userRequest){
        return new ResponseEntity<>(this.workerService.createUser(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.workerService.loginUser(userRequest), HttpStatus.OK);
    }

    @PutMapping("/updateuser")
    public ResponseEntity<AuthResponse>update(@RequestBody @Valid AuthCreateUserRequest userRequest){
        return  new ResponseEntity<>(this.workerService.updateUser(userRequest), HttpStatus.OK);
    }

    @DeleteMapping("/deleteuser/{username}")
    public ResponseEntity<String>deleteuser(@PathVariable String username){
        String mensajedeRespuesta= workerService.deleteworkerbyUsername(username);
        return new ResponseEntity<>(mensajedeRespuesta, HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<UserListDTO>> getAllUsers(){
        List<UserListDTO>users=workerService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
