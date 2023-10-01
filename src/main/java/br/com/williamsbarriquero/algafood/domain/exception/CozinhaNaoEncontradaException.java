package br.com.williamsbarriquero.algafood.domain.exception;

import java.io.Serial;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {
    @Serial
    private static final long serialVersionUID = 861804620197161835L;

    public CozinhaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
    
    public CozinhaNaoEncontradaException(Long cozinhaId) {
        this(String.format("N?o existe um cadastro de cozinha com c?digo %d", cozinhaId));
    }   
}   