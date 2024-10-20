package com.serverpet.server.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.serverpet.server.DTO.UserListDTO;
import com.serverpet.server.Models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.serverpet.server.DTO.AuthCreateUserRequest;
import com.serverpet.server.DTO.AuthLoginRequest;
import com.serverpet.server.DTO.AuthResponse;
import com.serverpet.server.Models.WorkerEntity;
import com.serverpet.server.Repositories.WorkerRepository;
import com.serverpet.server.Util.JwtServicie;
import com.serverpet.server.Util.Roles;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class WorkerService  implements UserDetailsService{

 @Autowired
    private JwtServicie jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WorkerRepository workerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) {

        WorkerEntity workerEntity = workerRepository.findUserEntityByUsername(username).orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        // Añadir los roles
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + workerEntity.getRole().name()));

        // Añadir los permisos del rol
        workerEntity.getRole().getPermisos().forEach(permission ->
                authorityList.add(new SimpleGrantedAuthority(permission.name())));


        return new User(workerEntity.getUsername(), workerEntity.getPassword(), workerEntity.isEnabled(), workerEntity.isAccountNoExpired(), workerEntity.isCredentialNoExpired(), workerEntity.isAccountNoLocked(), authorityList);
    }

    public AuthResponse createUser(AuthCreateUserRequest createRoleRequest) {

        String username = createRoleRequest.username();
        String password = createRoleRequest.password();
        Roles role=createRoleRequest.role();
        String email=createRoleRequest.email();
        Integer phone=createRoleRequest.phone();


        WorkerEntity workerEntity = WorkerEntity .builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .phone(phone)
                .roles(role)  // Ajustado para usar enum
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialNoExpired(true)
                .build();

       WorkerEntity userSaved = workerRepository.save(workerEntity);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // Añadir el rol
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userSaved.getRole().name()));

        // Añadir los permisos del rol
        userSaved.getRole().getPermisos().forEach(permission ->
                authorities.add(new SimpleGrantedAuthority(permission.name())));

        Authentication authentication = new UsernamePasswordAuthenticationToken(userSaved, null, authorities);
        String accessToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(username, "User created successfully", accessToken, true);
        return authResponse;
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {

        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);
        AuthResponse authResponse = new AuthResponse(username, "User loged succesfully", accessToken, true);
        return authResponse;
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException(String.format("Invalid username or password"));
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    public AuthResponse updateUser(AuthCreateUserRequest createRoleRequest) {
        // Buscar el usuario por su nombre de usuario
        WorkerEntity workerEntity = workerRepository.findUserEntityByUsername(createRoleRequest.username())
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + createRoleRequest.username() + " no existe."));

        // Actualizar los campos del usuario
        if (createRoleRequest.password() != null && !createRoleRequest.password().isEmpty()) {
            workerEntity.setPassword(passwordEncoder.encode(createRoleRequest.password()));
        }

        if (createRoleRequest.role() != null) {
            workerEntity.setRoles(createRoleRequest.role());  // Actualiza el rol si es necesario
        }
        if (createRoleRequest.email() != null) {
            workerEntity.setEmail(createRoleRequest.email());
        }
        if (createRoleRequest.phone() != null) {
            workerEntity.setPhone(createRoleRequest.phone());
        }

        // Guardar el usuario actualizado en la base de datos
        WorkerEntity updatedUser = workerRepository.save(workerEntity);

        // Crear lista de roles/permisos
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + updatedUser.getRole().name()));
        updatedUser.getRole().getPermisos().forEach(permission ->
                authorities.add(new SimpleGrantedAuthority(permission.name())));

        // Crear un nuevo token JWT
        Authentication authentication = new UsernamePasswordAuthenticationToken(updatedUser, null, authorities);
        String accessToken = jwtUtils.createToken(authentication);

        // Retornar la respuesta de autenticación
        return new AuthResponse(updatedUser.getUsername(), "User updated successfully", accessToken, true);
    }

    public String deleteworkerbyUsername(String username){
        WorkerEntity workerEntity = workerRepository.findUserEntityByUsername(username).orElseThrow(()->new UsernameNotFoundException("El usuario " + username + " no existe."));
        workerRepository.delete(workerEntity);
        return "el usuario "+ username + " ha sido eliminado";

    }

    public List<UserListDTO>getAllUsers(){
        List<WorkerEntity>users=workerRepository.findAll();
        return  users.stream().map(user->new UserListDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone()
        )).collect(Collectors.toList());
    }

}
