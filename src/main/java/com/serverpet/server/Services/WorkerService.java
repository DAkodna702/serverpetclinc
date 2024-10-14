package com.serverpet.server.Services;

import com.serverpet.server.Models.WorkerEntity;

public interface WorkerService {
    void save(WorkerEntity workerEntity);
    void update(WorkerEntity workerEntity);

}
