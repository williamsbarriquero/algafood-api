package br.com.williamsbarriquero.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteModel {

    private Long id;
    private String nome;
    private BigDecimal precoFrete;
    private CozinhaModel cozinha;
}
