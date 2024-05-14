package com.upc.api_examen_parcial_202120632.iservices.services;

import com.upc.api_examen_parcial_202120632.entities.Cinema;
import com.upc.api_examen_parcial_202120632.iservices.ICinemaService;
import com.upc.api_examen_parcial_202120632.repositories.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CinemaService implements ICinemaService {
    @Autowired
    private CinemaRepository rgpaCinemaRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Cinema insert(Cinema rgpaCinema) {
        return rgpaCinemaRepository.save(rgpaCinema);
    }
}
