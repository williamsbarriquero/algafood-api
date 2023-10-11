package br.com.williamsbarriquero.algafood.domain.service;

import br.com.williamsbarriquero.algafood.domain.exception.RestauranteNaoEncontradoException;
import br.com.williamsbarriquero.algafood.domain.model.Restaurante;
import br.com.williamsbarriquero.algafood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroRestauranteService {

    private static final String MSG_RESTAURANTE_NAO_ENCONTRADO
            = "Não existe um cadastro de restaurante com código %d";

    private final CadastroCozinhaService cadastroCozinha;
    private final RestauranteRepository restauranteRepository;

    public CadastroRestauranteService(final CadastroCozinhaService cadastroCozinha,
                                      final RestauranteRepository restauranteRepository) {
        this.cadastroCozinha = cadastroCozinha;
        this.restauranteRepository = restauranteRepository;
    }

    @Transactional
    public Restaurante salvar(final Restaurante restaurante) {
        final var cozinhaId = restaurante.getCozinha().getId();

        final var cozinha = cadastroCozinha.buscarOuFalhar(cozinhaId);

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }

    public Restaurante buscarOuFalhar(final Long restauranteId) {
        return restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(restauranteId));
    }
}
