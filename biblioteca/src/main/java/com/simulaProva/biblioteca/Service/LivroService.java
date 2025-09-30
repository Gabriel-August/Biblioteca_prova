package com.simulaProva.biblioteca.Service;

import com.simulaProva.biblioteca.Dto.LivroDto;
import com.simulaProva.biblioteca.Entity.Livro;
import com.simulaProva.biblioteca.Repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Transactional
    public LivroDto criarLivro(LivroDto livroDto) {
        Livro novoLivro = new Livro();
        // MUDANÇA 2: Usar o método privado para mapear os dados
        mapearDtoParaEntidade(livroDto, novoLivro);

        Livro livroSalvo = livroRepository.save(novoLivro);
        return new LivroDto(livroSalvo);
    }

    @Transactional(readOnly = true)
    public List<LivroDto> listarTodos() {
        List<Livro> livros = livroRepository.findAll();
        return livros.stream()
                .map(LivroDto::new) // Forma mais concisa de escrever a mesma coisa
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LivroDto buscarPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado com o ID: " + id));
        return new LivroDto(livro);
    }

    @Transactional
    public LivroDto atualizarLivro(Long id, LivroDto livroDto) {
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado com o ID: " + id));


        mapearDtoParaEntidade(livroDto, livroExistente);

        Livro livroAtualizado = livroRepository.save(livroExistente);
        return new LivroDto(livroAtualizado);
    }

    @Transactional
    public void deletarLivro(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new EntityNotFoundException("Livro não encontrado com o ID: " + id);
        }
        livroRepository.deleteById(id);
    }

    // MUDANÇA 2: Método privado para evitar repetição de código
    private void mapearDtoParaEntidade(LivroDto dto, Livro entidade) {
        entidade.setTitulo(dto.getTitulo());
        entidade.setAutor(dto.getAutor());
        entidade.setEditora(dto.getEditora());
        entidade.setAnoDePublicacao(dto.getAnoDePublicacao());
        entidade.setPreco(dto.getPreco());
    }
}
