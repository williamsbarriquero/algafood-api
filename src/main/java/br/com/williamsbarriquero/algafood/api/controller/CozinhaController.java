package br.com.williamsbarriquero.algafood.api.controller;

import br.com.williamsbarriquero.algafood.domain.exception.EntidadeEmUsoException;
import br.com.williamsbarriquero.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.williamsbarriquero.algafood.domain.model.Cozinha;
import br.com.williamsbarriquero.algafood.domain.repository.CozinhaRepository;
import br.com.williamsbarriquero.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    private final CozinhaRepository cozinhaRepository;
    private final CadastroCozinhaService cadastroCozinha;

    public CozinhaController(CozinhaRepository cozinhaRepository, CadastroCozinhaService cadastroCozinha) {
        this.cozinhaRepository = cozinhaRepository;
        this.cadastroCozinha = cadastroCozinha;
    }

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);

        return cozinha.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return cadastroCozinha.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {

        Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(cozinhaId);

        if (cozinhaAtual.isPresent()) {
            BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");

            var cozinhaSalva = cadastroCozinha.salvar(cozinhaAtual.get());

            return ResponseEntity.ok(cozinhaSalva);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Void> remover(@PathVariable Long cozinhaId) {
        try {
            cadastroCozinha.excluir(cozinhaId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeEmUsoException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
