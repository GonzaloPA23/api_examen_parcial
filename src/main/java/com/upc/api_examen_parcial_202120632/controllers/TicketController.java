package com.upc.api_examen_parcial_202120632.controllers;

import com.upc.api_examen_parcial_202120632.dtos.TicketDTO;
import com.upc.api_examen_parcial_202120632.entities.Ticket;
import com.upc.api_examen_parcial_202120632.iservices.ITicketService;
import com.upc.api_examen_parcial_202120632.util.DTOConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Ticket", description = "Ticket API")
@RestController
@RequestMapping("/api")
public class TicketController {
    @Autowired
    private ITicketService rgpaTicketService;
    @Autowired
    private DTOConverter rgpaDTOConverter;

    @PreAuthorize("hasAuthority('SALESPERSON')")
    @Operation(summary = "Inserta un nuevo ticket")
    @PostMapping("/ticket")
    public ResponseEntity<?> insert(@RequestBody TicketDTO rgpaTicketDTO){
        try {
            Ticket rgpaTicket = rgpaDTOConverter.convertToEntity(rgpaTicketDTO, Ticket.class);
            rgpaTicket = rgpaTicketService.insert(rgpaTicket);
            TicketDTO rgpaTicketDTO1 = rgpaDTOConverter.convertToDto(rgpaTicket, TicketDTO.class);
            return ResponseEntity.ok(rgpaTicketDTO1);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('SUPERVISOR')")
    @Operation(summary = "Obtiene el ticket por id")
    @GetMapping("/ventas")
    public ResponseEntity<?> rgpaRecaudationTicketsXCinema(){
        try {
            return ResponseEntity.ok(rgpaTicketService.rgpaRecaudationTicketsXCinema());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
