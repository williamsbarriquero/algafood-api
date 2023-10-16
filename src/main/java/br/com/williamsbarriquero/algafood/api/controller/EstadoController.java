package br.com.williamsbarriquero.algafood.api.controller;

import br.com.williamsbarriquero.algafood.api.assembler.EstadoInputDisassembler;
import br.com.williamsbarriquero.algafood.api.assembler.EstadoModelAssembler;
import br.com.williamsbarriquero.algafood.api.model.EstadoModel;
import br.com.williamsbarriquero.algafood.api.model.input.EstadoInput;
import br.com.williamsbarriquero.algafood.domain.model.Estado;
import br.com.williamsbarriquero.algafood.domain.repository.EstadoRepository;
import br.com.williamsbarriquero.algafood.domain.service.CadastroEstadoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private final EstadoRepository estadoRepository;
    private final CadastroEstadoService cadastroEstado;
    private final EstadoModelAssembler estadoModelAssembler;
    private final EstadoInputDisassembler estadoInputDisassembler;

    public EstadoController(EstadoRepository estadoRepository,
                            CadastroEstadoService cadastroEstado,
                            EstadoModelAssembler estadoModelAssembler,
                            EstadoInputDisassembler estadoInputDisassembler) {
        this.estadoRepository = estadoRepository;
        this.cadastroEstado = cadastroEstado;
        this.estadoModelAssembler = estadoModelAssembler;
        this.estadoInputDisassembler = estadoInputDisassembler;
    }

    @GetMapping
    public List<EstadoModel> listar() {
        final var todosEstados = estadoRepository.findAll();

        return estadoModelAssembler.toCollectionModel(todosEstados);
    }

    @GetMapping("/{estadoId}")
    public EstadoModel buscar(@PathVariable Long estadoId) {
        final var estado = cadastroEstado.buscarOuFalhar(estadoId);

        return estadoModelAssembler.toModel(estado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@RequestBody @Valid final EstadoInput estadoInput) {
        var estado = estadoInputDisassembler.toDomainObject(estadoInput);
        estado = cadastroEstado.salvar(estado);

        return estadoModelAssembler.toModel(estado);
    }

    @PutMapping("/{estadoId}")
    public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput) {
        Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);

        estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);

        estadoAtual = cadastroEstado.salvar(estadoAtual);

        return estadoModelAssembler.toModel(estadoAtual);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        cadastroEstado.excluir(estadoId);
    }
}