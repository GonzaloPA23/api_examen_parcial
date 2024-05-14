package com.upc.api_examen_parcial_202120632.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long rgpaId;

    @Column(name = "price", nullable = false)
    private BigDecimal rgpaPrice;

    @Column(name = "rowNumber", nullable = false)
    private int rgpaNumberRow;

    @Column(name = "seatNumber", nullable = false)
    private int rgpaNumberSeat;

    @ManyToOne
    @JoinColumn(name = "cinemas_id", nullable = false)
    private Cinema rgpaCinema;
}
