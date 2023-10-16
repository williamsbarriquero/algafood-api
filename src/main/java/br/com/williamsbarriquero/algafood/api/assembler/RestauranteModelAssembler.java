package br.com.williamsbarriquero.algafood.api.assembler;

import br.com.williamsbarriquero.algafood.api.model.RestauranteModel;
import br.com.williamsbarriquero.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestauranteModelAssembler {

    private final ModelMapper modelMapper;

    public RestauranteModelAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RestauranteModel toModel(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteModel.class);
    }

    public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(this::toModel)
                .toList();
    }
}
