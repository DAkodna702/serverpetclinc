package com.serverpet.server.Models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "\"Mascot\"")
public class MascotEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;
    private  Integer years_old;
    private String race;
    private String sex;
    @Builder.Default
    private boolean isDeleted = false;



    @ManyToOne
    @JoinColumn(name = "userentidad_id")
    private UserEntity userentidad;

    @Builder.Default
    @OneToMany(mappedBy = "mascotentity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HistoriEntity>historiList= new ArrayList<>();

}
