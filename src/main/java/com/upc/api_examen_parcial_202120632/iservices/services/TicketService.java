package com.upc.api_examen_parcial_202120632.iservices.services;

import com.upc.api_examen_parcial_202120632.dtos.response.RecaudationTicketsXCinemaResponseDTO;
import com.upc.api_examen_parcial_202120632.entities.Cinema;
import com.upc.api_examen_parcial_202120632.entities.Ticket;
import com.upc.api_examen_parcial_202120632.iservices.ITicketService;
import com.upc.api_examen_parcial_202120632.repositories.CinemaRepository;
import com.upc.api_examen_parcial_202120632.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private TicketRepository rgpaTicketRepository;
    @Autowired
    private CinemaRepository rgpaCinemaRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Ticket insert (Ticket rgpaTicket){
        Cinema rgpaCinema = rgpaCinemaRepository.findById(rgpaTicket.getRgpaCinema().getRgpaId()).orElse(null);

        assert rgpaCinema != null;
        if (rgpaCinema.getRgpaCapacity() - 1 < 0 ){
            throw new RuntimeException("No hay capacidad para más tickets");
        }

        rgpaCinema.setRgpaCapacity(rgpaCinema.getRgpaCapacity() - 1);
        rgpaCinemaRepository.save(rgpaCinema);

        return rgpaTicketRepository.save(rgpaTicket);
    }

    @Override
    public List<RecaudationTicketsXCinemaResponseDTO> rgpaRecaudationTicketsXCinema(){
        return rgpaCinemaRepository.rgpaRecaudationTicketsXCinema();
    }

}
