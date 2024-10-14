package com.serverpet.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.serverpet.server.Models.MascotEntity;

@Repository
public interface MascotRepository extends JpaRepository<MascotEntity, Long> {
    
    @Modifying
    @Query("UPDATE MascotEntity m SET m.isDeleted = true WHERE m.id = :id")
    void softDelete(@Param("id") Long id);

}
