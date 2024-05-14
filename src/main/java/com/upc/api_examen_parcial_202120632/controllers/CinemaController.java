package com.upc.api_examen_parcial_202120632.controllers;

import com.upc.api_examen_parcial_202120632.dtos.CinemaDTO;
import com.upc.api_examen_parcial_202120632.entities.Cinema;
import com.upc.api_examen_parcial_202120632.iservices.ICinemaService;
import com.upc.api_examen_parcial_202120632.util.DTOConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Cinema", description = "Cinema API")
@RestController
@RequestMapping("/api")
public class CinemaController {
    @Autowired
    private ICinemaService rgpaCinemaService;
    @Autowired
    private DTOConverter rgpaDTOConverter;

    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    @Operation(summary = "Inserta un nuevo cine")
    @PostMapping("/cinema")
    public ResponseEntity<?> insert(@RequestBody CinemaDTO rgpaCinemaDTO){
        try {
            Cinema rgpaCinema = rgpaDTOConverter.convertToEntity(rgpaCinemaDTO, Cinema.class);
            rgpaCinema = rgpaCinemaService.insert(rgpaCinema);
            CinemaDTO rgpaCinemaDTO1 = rgpaDTOConverter.convertToDto(rgpaCinema, CinemaDTO.class);
            return ResponseEntity.ok(rgpaCinemaDTO1);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
