package com.simulaProva.biblioteca.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O título não pode ser vazio.")
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;
    @NotBlank(message = "O autor não pode ser vazio.")
    @Column(name = "autor", nullable = false, length = 100)
    private String autor;
    private String editora;
    private int anoDePublicacao;
    private BigDecimal preco;
}
