package com.serverpet.server.Persistence.ImplementPersistence;

import java.util.List;


import com.serverpet.server.Models.MascotEntity;
import com.serverpet.server.Persistence.DAOMascot;
import com.serverpet.server.Repositories.MascotRepository;
import org.springframework.stereotype.Service;

@Service
public class DAOMascotImplement implements DAOMascot {
    private static DAOMascotImplement instance;

    private final MascotRepository mascotrepository;
    
    private DAOMascotImplement(MascotRepository mascotRepository) {
        this.mascotrepository = mascotRepository;
    }

    public static synchronized DAOMascotImplement getInstance(MascotRepository mascotrepository) {
        if (instance == null) {
            instance = new DAOMascotImplement(mascotrepository);
        }
        return instance;
    }

    @Override
    public void save(MascotEntity mascotEntity) {
        mascotrepository.save(mascotEntity);
    }

    @Override
    public void update(MascotEntity mascotEntity) {
       mascotrepository.save(mascotEntity);
    }

    @Override
    public List<MascotEntity> findAll() {
       return mascotrepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        mascotrepository.softDelete(id);
    }


}
