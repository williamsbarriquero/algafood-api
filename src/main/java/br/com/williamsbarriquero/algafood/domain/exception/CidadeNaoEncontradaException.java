package br.com.williamsbarriquero.algafood.domain.exception;

import java.io.Serial;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

    @Serial
    private static final long serialVersionUID = 8713011333074448872L;

    public CidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
    
    public CidadeNaoEncontradaException(Long cidadeId) {
        this(String.format("N?o existe um cadastro de cidade com c?digo %d", cidadeId));
    }   
} 