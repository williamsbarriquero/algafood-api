package br.com.williamsbarriquero.algafood.api.model.mixin;

import br.com.williamsbarriquero.algafood.domain.model.Cozinha;
import br.com.williamsbarriquero.algafood.domain.model.Endereco;
import br.com.williamsbarriquero.algafood.domain.model.FormaPagamento;
import br.com.williamsbarriquero.algafood.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;

public abstract class RestauranteMixin {

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private List<FormaPagamento> formasPagamentos;

    @JsonIgnore
    private List<Produto> produtos;
}
