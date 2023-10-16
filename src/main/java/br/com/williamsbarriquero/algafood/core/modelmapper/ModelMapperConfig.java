package br.com.williamsbarriquero.algafood.core.modelmapper;

import br.com.williamsbarriquero.algafood.api.model.RestauranteModel;
import br.com.williamsbarriquero.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {


    @Bean
    public ModelMapper modelMapper() {
        final var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
                .addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);

        return modelMapper;
    }
}
