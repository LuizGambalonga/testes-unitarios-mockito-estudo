package com.gambalongasys.database.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "DATABASE", schema = "GAMBALONGA_OWNER")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DatabaseModel{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nome;

    @Size(max = 14, min = 14, message = "O campo cpf deve conter no minimo 14 caracter")
    private String cpf;

    @Size(max = 18, min = 18, message = "O campo cnpj deve conter no minimo 18 caracter")
    private String cnpj;
}
