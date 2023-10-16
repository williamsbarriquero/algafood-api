package br.com.williamsbarriquero.algafood.api.assembler;

import br.com.williamsbarriquero.algafood.api.model.CozinhaModel;
import br.com.williamsbarriquero.algafood.api.model.RestauranteModel;
import br.com.williamsbarriquero.algafood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestauranteModelAssembler {

    public RestauranteModel toModel(Restaurante restaurante) {
        CozinhaModel cozinhaModel = new CozinhaModel();
        cozinhaModel.setId(restaurante.getCozinha().getId());
        cozinhaModel.setNome(restaurante.getCozinha().getNome());

        RestauranteModel restauranteModel = new RestauranteModel();
        restauranteModel.setId(restaurante.getId());
        restauranteModel.setNome(restaurante.getNome());
        restauranteModel.setTaxaFrete(restaurante.getTaxaFrete());
        restauranteModel.setCozinha(cozinhaModel);
        return restauranteModel;
    }

    public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(this::toModel)
                .toList();
    }
}
