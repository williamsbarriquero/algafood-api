package br.com.williamsbarriquero.algafood.domain.exception;

import java.io.Serial;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

    @Serial
    private static final long serialVersionUID = 7004821683625728925L;

    private static final String MSG_ESTADO_NAO_ENCONTRADO =
            "Não existe um cadastro de estado com código %d";

    public EstadoNaoEncontradoException(String message) {
        super(message);
    }

    public EstadoNaoEncontradoException(Long id) {
        this(String.format(MSG_ESTADO_NAO_ENCONTRADO, id));
    }
}
