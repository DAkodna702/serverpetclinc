package com.serverpet.server.Services;

import java.util.List;

import com.serverpet.server.Models.HistoriEntity;

public interface HistoriService {

     void save(HistoriEntity historiEntity);

    void update(HistoriEntity historiEntity);

    List<HistoriEntity> findByMascotIdAndPendingState(Long id);

    List<HistoriEntity> findByMascotIdOrderByState(Long id);

    List<HistoriEntity> findByEmpleadoIdAndPendingState(Long id);

    List<HistoriEntity> findByEmpleadoIdOrderByState(Long id);

}
