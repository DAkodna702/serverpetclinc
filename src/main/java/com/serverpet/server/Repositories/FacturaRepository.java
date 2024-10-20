package com.serverpet.server.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serverpet.server.Models.FacturaEntity;

import java.util.List;

public interface FacturaRepository extends JpaRepository<FacturaEntity,Long>{

    List<FacturaEntity> findAll();

}
