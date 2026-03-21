package com.itsqmet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "mascotas")
@Data
@NoArgsConstructor
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30, min = 3)
    private String nombre;

    private String especie;

    private int edad;

    @OneToMany(mappedBy = "mascota")
    private List<Consulta> consultas;
}
