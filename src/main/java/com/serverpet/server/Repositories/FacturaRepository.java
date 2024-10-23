package com.serverpet.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serverpet.server.Models.FacturaEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FacturaRepository extends JpaRepository<FacturaEntity,Long>{


    List<FacturaEntity> findAll();

    FacturaEntity findByHistorieEntity_Id(Long historiaId);


    @Query("SELECT f FROM FacturaEntity f WHERE f.historieEntity.mascotentity.id = :mascotId")
    List<FacturaEntity> findAllByMascotId(Long mascotId);


}
