package com.serverpet.server.Persistence;

import java.util.List;

import com.serverpet.server.Models.HistoriEntity;

public interface DAOHistori {
    void save(HistoriEntity historiEntity);

    void update(HistoriEntity historiEntity);

    List<HistoriEntity> findByMascotIdAndPendingState(Long id);

    List<HistoriEntity> findByMascotIdOrderByState(Long id);

    List<HistoriEntity> findByWorkerIdAndPendingState(Long id);

    List<HistoriEntity> findByWorkerIdOrderByState(Long id);

}
