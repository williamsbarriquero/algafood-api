package br.com.williamsbarriquero.algafood.api.model.mixin;

import br.com.williamsbarriquero.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public abstract class CozinhaMixin {

    @JsonIgnore
    private List<Restaurante> restaurantes;
}
