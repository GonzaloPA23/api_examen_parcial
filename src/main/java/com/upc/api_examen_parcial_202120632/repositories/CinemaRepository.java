package com.upc.api_examen_parcial_202120632.repositories;

import com.upc.api_examen_parcial_202120632.dtos.response.RecaudationTicketsXCinemaResponseDTO;
import com.upc.api_examen_parcial_202120632.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
   //SELECT SUM(price), count(tickets.id), c.id, c.description FROM tickets
   //JOIN cinemas c on tickets.cinemas_id = c.id
   //group by c.id, c.description;
    @Query("SELECT NEW com.upc.api_examen_parcial_202120632.dtos.response.RecaudationTicketsXCinemaResponseDTO (SUM(t.rgpaPrice), COUNT(t.rgpaId), c.rgpaId, c.rgpaDescription) FROM Ticket t JOIN t.rgpaCinema c GROUP BY c.rgpaId, c.rgpaDescription")
    List<RecaudationTicketsXCinemaResponseDTO> rgpaRecaudationTicketsXCinema();
}
