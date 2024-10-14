package com.serverpet.server.Persistence.ImplementPersistence;

import java.util.List;



import com.serverpet.server.Models.HistoriEntity;
import com.serverpet.server.Persistence.DAOHistori;
import com.serverpet.server.Repositories.HistoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DAOHistoriImplement implements DAOHistori {

    private static DAOHistoriImplement instance;

    @Autowired
    private final HistoriRepository historirepository;

    private DAOHistoriImplement(HistoriRepository historiRepository) {
        this.historirepository = historiRepository;
    }

    public static synchronized DAOHistoriImplement getInstance(HistoriRepository historiRepository) {
        if (instance == null) {
            instance = new DAOHistoriImplement(historiRepository);
        }
        return instance;
    }
    

    @Override
    public void save(HistoriEntity historiEntity) {
        historirepository.save(historiEntity);
    }

    @Override
    public void update(HistoriEntity historiEntity) {

        historirepository.save(historiEntity);
    }

    @Override
    public List<HistoriEntity> findByMascotIdAndPendingState(Long id) {
        return historirepository.findByMascotIdAndPendingState(id);
    }

    @Override
    public List<HistoriEntity> findByMascotIdOrderByState(Long id) {
        return historirepository.findByMascotIdOrderByState(id);
    }

    @Override
    public List<HistoriEntity> findByWorkerIdAndPendingState(Long id) {
        return historirepository.findByWorkerIdAndPendingState(id);
    }

    @Override
    public List<HistoriEntity> findByWorkerIdOrderByState(Long id) {
        return historirepository.findByWorkerIdOrderByState(id);
    }

}
