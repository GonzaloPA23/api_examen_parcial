package com.upc.api_examen_parcial_202120632.repositories;

import com.upc.api_examen_parcial_202120632.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
