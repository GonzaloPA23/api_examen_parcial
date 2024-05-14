package com.upc.api_examen_parcial_202120632.iservices;

import com.upc.api_examen_parcial_202120632.dtos.response.RecaudationTicketsXCinemaResponseDTO;
import com.upc.api_examen_parcial_202120632.entities.Ticket;

import java.util.List;

public interface ITicketService {
    Ticket insert(Ticket rgpaTicket);
    List<RecaudationTicketsXCinemaResponseDTO> rgpaRecaudationTicketsXCinema();
}
