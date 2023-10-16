package br.com.williamsbarriquero.algafood.api.controller;

import br.com.williamsbarriquero.algafood.api.assembler.RestauranteInputDisassembler;
import br.com.williamsbarriquero.algafood.api.assembler.RestauranteModelAssembler;
import br.com.williamsbarriquero.algafood.api.model.RestauranteModel;
import br.com.williamsbarriquero.algafood.api.model.input.RestauranteInput;
import br.com.williamsbarriquero.algafood.domain.exception.CozinhaNaoEncontradaException;
import br.com.williamsbarriquero.algafood.domain.exception.NegocioException;
import br.com.williamsbarriquero.algafood.domain.model.Restaurante;
import br.com.williamsbarriquero.algafood.domain.repository.RestauranteRepository;
import br.com.williamsbarriquero.algafood.domain.service.CadastroRestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteRepository restauranteRepository;
    private final CadastroRestauranteService cadastroRestaurante;
    private final RestauranteModelAssembler restauranteModelAssembler;
    private final RestauranteInputDisassembler restauranteInputDisassembler;

    public RestauranteController(
            final RestauranteRepository restauranteRepository,
            final CadastroRestauranteService cadastroRestaurante,
            RestauranteModelAssembler restauranteModelAssembler,
            RestauranteInputDisassembler restauranteInputDisassembler) {
        this.restauranteRepository = restauranteRepository;
        this.cadastroRestaurante = cadastroRestaurante;
        this.restauranteModelAssembler = restauranteModelAssembler;
        this.restauranteInputDisassembler = restauranteInputDisassembler;
    }

    @GetMapping
    public List<RestauranteModel> listar() {
        return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid final RestauranteInput restauranteInput) {
        try {
            final var restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
            return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restaurante));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @GetMapping("/{restauranteId}")
    public RestauranteModel buscar(@PathVariable final Long restauranteId) {
        final var restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        return restauranteModelAssembler.toModel(restaurante);
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable final Long restauranteId,
                                      @RequestBody @Valid final RestauranteInput restauranteInput) {
        try {
            final var restauranteAtual = cadastroRestaurante.buscarOuFalhar(restauranteId);

            restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);

            return restauranteModelAssembler.toModel(cadastroRestaurante.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
