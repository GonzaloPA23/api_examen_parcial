package com.upc.api_examen_parcial_202120632.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cinemas")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long rgpaId;

    @Column(name = "description", nullable = false)
    private String rgpaDescription;

    @Column(name = "address", nullable = false)
    private String rgpaAddress;

    @Column(name = "dateOpening", nullable = false)
    private LocalDate rgpaDateOpening;

    @Column(name = "capacity", nullable = false)
    private int rgpaCapacity;
}
