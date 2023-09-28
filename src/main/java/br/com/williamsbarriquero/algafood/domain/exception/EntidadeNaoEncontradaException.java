package br.com.williamsbarriquero.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 3993431480077089969L;

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
