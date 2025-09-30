package com.simulaProva.biblioteca.Entity;

import jakarta.persistence.Entity;
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

    private String titulo;
    private String autor;
    private String editora;
    private int anoDePublicacao;
    private BigDecimal Preco;
}
