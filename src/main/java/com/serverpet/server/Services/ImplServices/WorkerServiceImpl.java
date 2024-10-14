package com.serverpet.server.Services.ImplServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serverpet.server.Models.WorkerEntity;
import com.serverpet.server.Persistence.DAOWorker;
import com.serverpet.server.Services.WorkerService;


@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private final DAOWorker daoWorker;
    
    public WorkerServiceImpl(DAOWorker daoWorker) {
        this.daoWorker = daoWorker;
    }


    @Override
    public void save(WorkerEntity workerEntity) {
        daoWorker.save(workerEntity);
    }
       

    @Override
    public void update(WorkerEntity workerEntity) {
        daoWorker.update(workerEntity);
    }


}
