package br.com.williamsbarriquero.algafood.domain.exception;

import java.io.Serial;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

    @Serial
    private static final long serialVersionUID = -3083524671443437858L;

    public RestauranteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    
    public RestauranteNaoEncontradoException(Long restauranteId) {
        this(String.format("N?o existe um cadastro de restaurante com c?digo %d", restauranteId));
    }   
}       