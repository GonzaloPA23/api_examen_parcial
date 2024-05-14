package com.upc.api_examen_parcial_202120632.dtos;

import com.upc.api_examen_parcial_202120632.entities.Cinema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private Long rgpaId;
    private BigDecimal rgpaPrice;
    private int rgpaNumberRow;
    private int rgpaNumberSeat;
    private Cinema rgpaCinema;
}
