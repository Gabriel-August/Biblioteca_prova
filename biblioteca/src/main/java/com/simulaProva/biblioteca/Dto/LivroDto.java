package com.simulaProva.biblioteca.Dto;

import com.simulaProva.biblioteca.Entity.Livro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LivroDto {  //Dto

 private long id;
 private String titulo;
 private String autor;
 private String editora;
 private int anoDePublicacao;
 private BigDecimal preco;

 public LivroDto(Livro entidade){
  this.id = entidade.getId();
  this.titulo = entidade.getTitulo();
  this.autor = entidade.getAutor();
  this.editora = entidade.getEditora();
  this.preco = entidade.getPreco();
 }


}
