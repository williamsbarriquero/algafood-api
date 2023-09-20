package br.com.williamsbarriquero.algafood.domain.exception;

import java.io.Serial;

public class EntidadeNaoEncontradaException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3993431480077089969L;

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
