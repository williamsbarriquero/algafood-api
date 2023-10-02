package br.com.williamsbarriquero.algafood.domain.exception;

import java.io.Serial;

public abstract class EntidadeNaoEncontradaException extends NegocioException {

    @Serial
    private static final long serialVersionUID = 3993431480077089969L;

    protected EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
