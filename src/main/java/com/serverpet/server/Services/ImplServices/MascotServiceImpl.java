package com.serverpet.server.Services.ImplServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serverpet.server.Models.MascotEntity;
import com.serverpet.server.Persistence.DAOMascot;
import com.serverpet.server.Services.MascotService;

@Service
public class MascotServiceImpl implements MascotService {

     @Autowired
    private final DAOMascot daomascot;
    
    public MascotServiceImpl(DAOMascot daomascot) {
        this.daomascot = daomascot;
    }

    @Override
    public void save(MascotEntity mascotEntity) {
        daomascot.save(mascotEntity);
    }

    @Override
    public void update(MascotEntity mascotEntity) {
        daomascot.update(mascotEntity);
    }

    @Override
    public List<MascotEntity> findAll() {
        return daomascot.findAll();
    }

    @Override
    public void deleteById(Long id) {
       daomascot.deleteById(id);
    }


}
