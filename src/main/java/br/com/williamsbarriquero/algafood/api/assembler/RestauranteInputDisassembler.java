package br.com.williamsbarriquero.algafood.api.assembler;

import br.com.williamsbarriquero.algafood.api.model.input.RestauranteInput;
import br.com.williamsbarriquero.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RestauranteInputDisassembler {

    private final ModelMapper modelMapper;

    public RestauranteInputDisassembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Restaurante toDomainObject(RestauranteInput restauranteInput) {
        return modelMapper.map(restauranteInput, Restaurante.class);
    }
}
