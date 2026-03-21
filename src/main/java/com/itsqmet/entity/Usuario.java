package com.itsqmet.entity;

import com.itsqmet.role.Rol;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 10, min = 3)
    private String nombre;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany(mappedBy = "usuario")
    private List<Consulta> consultas;

}
