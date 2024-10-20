package com.serverpet.server.Repositories;

import com.serverpet.server.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.serverpet.server.Models.MascotEntity;

import java.util.List;

@Repository
public interface MascotRepository extends JpaRepository<MascotEntity, Long> {

    @Modifying
    @Query("UPDATE MascotEntity m SET m.isDeleted = true WHERE m.id = :id")
    void softDelete(@Param("id") Long id);

    @Query("SELECT m FROM MascotEntity m WHERE m.isDeleted = false")
    List<MascotEntity> findAllMascots();

    // Buscar todas las mascotas de un usuario específico
    @Query("SELECT m FROM MascotEntity m WHERE m.userentidad = :user AND m.isDeleted = false")
    List<MascotEntity> findAllByUser(@Param("user") UserEntity user);

    // Buscar mascotas por nombre exacto
    @Query("SELECT m FROM MascotEntity m WHERE m.name = :name AND m.isDeleted = false")
    List<MascotEntity> findByName(@Param("name") String name);

    // Buscar mascotas que contengan el nombre (coincidencia parcial)
    @Query("SELECT m FROM MascotEntity m WHERE m.name LIKE %:name% AND m.isDeleted = false")
    List<MascotEntity> findByNameContaining(@Param("name") String name);

    @Query("SELECT m FROM MascotEntity m WHERE m.isDeleted = false")
    List<MascotEntity> findAllActiveMascots();



}
