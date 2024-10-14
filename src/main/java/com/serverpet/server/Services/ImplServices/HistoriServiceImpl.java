package com.serverpet.server.Services.ImplServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serverpet.server.Models.HistoriEntity;
import com.serverpet.server.Persistence.DAOHistori;
import com.serverpet.server.Services.HistoriService;


@Service
public class HistoriServiceImpl implements HistoriService {

    @Autowired
    private final DAOHistori daoHistori;

    public HistoriServiceImpl(DAOHistori daoHistori) {
        this.daoHistori = daoHistori;
    }

    @Override
    public void save(HistoriEntity historiEntity) {
        daoHistori.save(historiEntity);
    }

    @Override
    public void update(HistoriEntity historiEntity) {
        daoHistori.update(historiEntity);
    }

    @Override
    public List<HistoriEntity> findByMascotIdAndPendingState(Long id) {
        return daoHistori.findByMascotIdAndPendingState(id);
    }

    @Override
    public List<HistoriEntity> findByMascotIdOrderByState(Long id) {
        return daoHistori.findByMascotIdOrderByState(id);
    }

    @Override
    public List<HistoriEntity> findByEmpleadoIdAndPendingState(Long id) {
        return daoHistori.findByWorkerIdAndPendingState(id);
    }

    @Override
    public List<HistoriEntity> findByEmpleadoIdOrderByState(Long id) {
        return daoHistori.findByWorkerIdOrderByState(id);
    }
}
