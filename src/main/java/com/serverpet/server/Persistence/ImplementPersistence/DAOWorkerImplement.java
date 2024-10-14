package com.serverpet.server.Persistence.ImplementPersistence;

import org.springframework.beans.factory.annotation.Autowired;

import com.serverpet.server.Models.WorkerEntity;
import com.serverpet.server.Persistence.DAOWorker;
import com.serverpet.server.Repositories.WorkerRepository;
import org.springframework.stereotype.Service;


@Service
public class DAOWorkerImplement implements DAOWorker {

     private static DAOWorkerImplement instance;

    @Autowired
    private final WorkerRepository workerRepository;
    
    private DAOWorkerImplement(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public static synchronized DAOWorkerImplement getInstance(WorkerRepository workerRepository) {
        if (instance == null) {
            instance = new DAOWorkerImplement(workerRepository);
        }
        return instance;
    }

    @Override
    public void save(WorkerEntity workerEntity) {
       workerRepository.save( workerEntity);
    }

    @Override
    public void update(WorkerEntity workerEntity) {
         workerRepository.save(workerEntity);
    }


}
