package com.upc.api_examen_parcial_202120632.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecaudationTicketsXCinemaResponseDTO {
    private BigDecimal rgpaTotalPrice;
    private Long rgpaQuantityTickets;
    private Long rgpaCinemaId;
    private String rgpaCinemaName;
}
