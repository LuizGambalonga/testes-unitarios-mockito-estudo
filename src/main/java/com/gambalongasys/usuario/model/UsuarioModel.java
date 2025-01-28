package com.gambalongasys.usuario.model;

import com.gambalongasys.database.model.DatabaseModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "usuario", schema = "GAMBALONGA_OWNER")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String senha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_database", referencedColumnName = "id", nullable = false)
    private DatabaseModel database;
}