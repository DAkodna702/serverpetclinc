package com.serverpet.server.Repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.serverpet.server.Models.*;


@Repository
public interface WorkerRepository extends JpaRepository<WorkerEntity, Long> {

    List<WorkerEntity> findAll();

    Optional<WorkerEntity> findUserEntityByUsername(String username);
    

}
