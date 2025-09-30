package com.simulaProva.biblioteca.Controller;

import com.simulaProva.biblioteca.Dto.LivroDto;
import com.simulaProva.biblioteca.Service.LivroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/livros")
public class LivroController {

    private final LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public ResponseEntity<List<LivroDto>> listarTodos() {
        List<LivroDto> livros = livroService.listarTodos();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> buscarPorId(@PathVariable Long id) {
        LivroDto livro = livroService.buscarPorId(id);
        return ResponseEntity.ok(livro);
    }

    @PostMapping
    public ResponseEntity<LivroDto> criarLivro(@RequestBody LivroDto livroDto) {
        LivroDto novoLivro = livroService.criarLivro(livroDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoLivro.getId())
                .toUri();

        return ResponseEntity.created(location).body(novoLivro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDto> atualizarLivro(@PathVariable Long id, @RequestBody LivroDto livroDto) {
        LivroDto livroAtualizado = livroService.atualizarLivro(id, livroDto);
        return ResponseEntity.ok(livroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        livroService.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
