package com.serverpet.server.Controllers;


import com.serverpet.server.DTO.AuthCreateUserRequest;
import com.serverpet.server.DTO.AuthLoginRequest;
import com.serverpet.server.DTO.AuthResponse;
import com.serverpet.server.DTO.UserListDTO;
import com.serverpet.server.Services.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping ("/auth/user")
public class AuthUser {

    @Autowired
    private UserServiceImpl userDetailService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest userRequest){
        return new ResponseEntity<>(this.userDetailService.createUser(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailService.loginUser(userRequest), HttpStatus.OK);
    }

    @PutMapping("/updateuser")
    public ResponseEntity<AuthResponse>update(@RequestBody @Valid AuthCreateUserRequest userRequest){
        return  new ResponseEntity<>(this.userDetailService.updateUser(userRequest), HttpStatus.OK);
    }

    @DeleteMapping("/deleteuser/{username}")
    public ResponseEntity<String>deleteuser(@PathVariable String username){
        String mensajedeRespuesta= userDetailService.deleteuserbyUsername(username);
        return new ResponseEntity<>(mensajedeRespuesta, HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<UserListDTO>> getAllUsers(){
        List<UserListDTO>users=userDetailService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
