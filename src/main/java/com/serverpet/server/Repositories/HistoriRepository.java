package com.serverpet.server.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serverpet.server.Models.HistoriEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriRepository extends JpaRepository<HistoriEntity, Long> {
      /* for mascot and state */
    @Query("SELECT h FROM HistoriEntity h WHERE h.mascotentity.id = :mascotId AND h.historistate = 'PENDIENTE'")
    List<HistoriEntity> findByMascotIdAndPendingState(@Param("mascotId") Long mascotId);

     @Query("SELECT h FROM HistoriEntity h WHERE h.mascotentity.id = :mascotId ORDER BY " +
           "CASE WHEN h.historistate = 'COMPLETADO' THEN 1 " +
           "WHEN h.historistate = 'POSPUESTO' THEN 2 " +
           "WHEN h.historistate = 'CANCELADO' THEN 3 ELSE 4 END")
     List<HistoriEntity> findByMascotIdOrderByState(@Param("mascotId") Long mascotId);



    @Query("SELECT h FROM HistoriEntity h WHERE h.workerEntity.id = :workerId AND h.historistate = 'PENDIENTE'")
    List<HistoriEntity> findByWorkerIdAndPendingState(@Param("workerId") Long workerId);

    @Query("SELECT h FROM HistoriEntity h WHERE h.workerEntity.id = :workerId ORDER BY " +
            "CASE WHEN h.historistate = 'COMPLETADO' THEN 1 " +
            "WHEN h.historistate = 'POSPUESTO' THEN 2 " +
            "WHEN h.historistate = 'CANCELADO' THEN 3 ELSE 4 END")
    List<HistoriEntity> findByWorkerIdOrderByState(@Param("workerId") Long workerId);


}
