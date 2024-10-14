package com.serverpet.server.Services;

import java.util.List;

import com.serverpet.server.Models.MascotEntity;

public interface MascotService {
     void save(MascotEntity mascotEntity);
    void update(MascotEntity mascotEntity);
    List<MascotEntity> findAll();
    void deleteById(Long id);

}
