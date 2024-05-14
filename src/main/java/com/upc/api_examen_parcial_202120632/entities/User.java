package com.upc.api_examen_parcial_202120632.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long rgpaId;

    @Column(name = "username", nullable = false, length = 60, unique = true)
    private String rgpaUsername;

    @Column(name = "password", nullable = false, length = 200)
    private String rgpaPassword;

    @Column(name = "enabled")
    private Boolean rgpaEnabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> rgpaRoles = new HashSet<>();
}
