package br.com.williamsbarriquero.algafood.api.model.mixin;

import br.com.williamsbarriquero.algafood.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public abstract class CidadeMixin {

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
    
}  