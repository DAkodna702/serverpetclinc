package com.serverpet.server.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serverpet.server.Models.FacturaEntity;
import com.serverpet.server.Repositories.FacturaRepository;



@Service
public class FacturaServiciesImpl {

    @Autowired
    private FacturaRepository facturaRepository;



    public void save(FacturaEntity facturaEntity) {
        facturaRepository.save(facturaEntity);
    }

}
