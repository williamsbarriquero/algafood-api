package br.com.williamsbarriquero.algafood.api.controller;

import br.com.williamsbarriquero.algafood.domain.exception.EntidadeEmUsoException;
import br.com.williamsbarriquero.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.williamsbarriquero.algafood.domain.model.Cidade;
import br.com.williamsbarriquero.algafood.domain.repository.CidadeRepository;
import br.com.williamsbarriquero.algafood.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

    private final CidadeRepository cidadeRepository;
    private final CadastroCidadeService cadastroCidade;

    public CidadeController(CidadeRepository cidadeRepository, CadastroCidadeService cadastroCidade) {
        this.cidadeRepository = cidadeRepository;
        this.cadastroCidade = cadastroCidade;
    }

    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
        Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);

        return cidade.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
        try {
            cidade = cadastroCidade.salvar(cidade);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<?> atualizar(@PathVariable Long cidadeId,
                                       @RequestBody Cidade cidade) {
        try {
            Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidadeId);

            if (cidadeAtual.isPresent()) {
                BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");

                var cidadeSalva = cadastroCidade.salvar(cidadeAtual.get());
                return ResponseEntity.ok(cidadeSalva);
            }

            return ResponseEntity.notFound().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId) {
        try {
            cadastroCidade.excluir(cidadeId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}