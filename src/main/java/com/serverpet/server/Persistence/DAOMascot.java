package com.serverpet.server.Persistence;

import java.util.List;

import com.serverpet.server.Models.MascotEntity;

public interface DAOMascot {
    void save(MascotEntity mascotEntity);
    void update(MascotEntity mascotEntity);
    List<MascotEntity> findAll();
    void deleteById(Long id);

}
