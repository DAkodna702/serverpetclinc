package com.serverpet.server.Persistence;

import com.serverpet.server.Models.WorkerEntity;

public interface DAOWorker {
    void save(WorkerEntity workerEntity);
    void update(WorkerEntity workerEntity);

}
