package br.com.williamsbarriquero.algafood.domain.exception;

import java.io.Serial;

public class NegocioException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3521574913155442535L;

    public NegocioException(String mensagem) {
        super(mensagem);
    }

}