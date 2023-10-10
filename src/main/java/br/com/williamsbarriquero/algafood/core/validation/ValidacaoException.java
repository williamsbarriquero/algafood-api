package br.com.williamsbarriquero.algafood.core.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.io.Serial;

@AllArgsConstructor
@Getter
public class ValidacaoException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -4805981579563444367L;

    private BindingResult bindingResult;
}
