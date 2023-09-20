package br.com.williamsbarriquero.algafood.domain.service;

import br.com.williamsbarriquero.algafood.domain.exception.EntidadeNaoEncontradaException;
import br.com.williamsbarriquero.algafood.domain.model.Cozinha;
import br.com.williamsbarriquero.algafood.domain.model.Restaurante;
import br.com.williamsbarriquero.algafood.domain.repository.CozinhaRepository;
import br.com.williamsbarriquero.algafood.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    private final CozinhaRepository cozinhaRepository;
    private final RestauranteRepository restauranteRepository;

    public CadastroRestauranteService(CozinhaRepository cozinhaRepository, RestauranteRepository restauranteRepository) {
        this.cozinhaRepository = cozinhaRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe cadastro de cozinha com o código %d", cozinhaId)
                ));

        restaurante.setCozinha(cozinha);

        return restauranteRepository.save(restaurante);
    }
}
