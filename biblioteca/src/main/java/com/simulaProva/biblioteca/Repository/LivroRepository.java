package com.simulaProva.biblioteca.Repository;

import com.simulaProva.biblioteca.Entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository <Livro, Long>   {
    //Implementação de buscas de livros por título
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
}
