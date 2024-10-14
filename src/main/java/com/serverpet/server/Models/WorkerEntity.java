package com.serverpet.server.Models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.serverpet.server.Util.Roles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"Empleados\"")
public class WorkerEntity implements UserDetails {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String nombre;
    private String password;
    private String email;
    private Integer phone;

    @Enumerated(EnumType.STRING)
    private Roles roles;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "account_No_Expired")
    private boolean accountNoExpired;

    @Column(name = "account_No_Locked")
    private boolean accountNoLocked;

    @Column(name = "credential_No_Expired")
    private boolean credentialNoExpired;

     @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {

         List<GrantedAuthority> authorities = roles.getPermisos().stream()
                 .map(permisosEnum -> new SimpleGrantedAuthority(permisosEnum.name()))
                 .collect(Collectors.toList());
         authorities.add(new SimpleGrantedAuthority("ROLE_" + roles.name()));

         return authorities;
     }



     @Builder.Default
     @OneToMany(mappedBy = "workerEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     private List<HistoriEntity> historiList = new ArrayList<>();

    
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNoExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNoLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialNoExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
