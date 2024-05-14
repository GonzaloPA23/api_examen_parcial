package com.upc.api_examen_parcial_202120632.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CinemaDTO {
    private Long rgpaId;
    private String rgpaDescription;
    private String rgpaAddress;
    private LocalDate rgpaDateOpening;
    private int rgpaCapacity;
}
