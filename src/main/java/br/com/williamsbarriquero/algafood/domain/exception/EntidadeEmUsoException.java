package br.com.williamsbarriquero.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends NegocioException {

    @Serial
    private static final long serialVersionUID = -4768519314633371116L;

    public EntidadeEmUsoException(String message) {
        super(message);
    }
}
