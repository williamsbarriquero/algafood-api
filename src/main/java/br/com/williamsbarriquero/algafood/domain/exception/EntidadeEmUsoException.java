package br.com.williamsbarriquero.algafood.domain.exception;

import java.io.Serial;

public class EntidadeEmUsoException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4768519314633371116L;

    public EntidadeEmUsoException(String message) {
        super(message);
    }
}
