package br.com.williamsbarriquero.algafood.api.model.mixin;

import br.com.williamsbarriquero.algafood.core.validation.Groups;
import br.com.williamsbarriquero.algafood.domain.model.Cozinha;
import br.com.williamsbarriquero.algafood.domain.model.Endereco;
import br.com.williamsbarriquero.algafood.domain.model.FormaPagamento;
import br.com.williamsbarriquero.algafood.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestauranteMixin {

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    @JsonIgnore
    private LocalDateTime dataAtualizacao;

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;

    @JsonIgnore
    private List<FormaPagamento> formasPagamentos = new ArrayList<>();

    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();
}
