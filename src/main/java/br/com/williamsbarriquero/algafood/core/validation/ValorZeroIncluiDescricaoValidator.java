package br.com.williamsbarriquero.algafood.core.validation;

import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.util.Objects;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object> {

    private String valorField;
    private String descricaoField;
    private String descricaoObrigatoria;

    @Override
    public void initialize(final ValorZeroIncluiDescricao constraint) {
        this.valorField = constraint.valorField();
        this.descricaoField = constraint.descricaoField();
        this.descricaoObrigatoria = constraint.descricaoObrigatoria();
    }

    @Override
    public boolean isValid(final Object objetoValidacao, ConstraintValidatorContext context) {
        boolean valido = true;

        try {
            final var valor =
                    (BigDecimal) Objects.requireNonNull(
                            BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), valorField)
                    ).getReadMethod().invoke(objetoValidacao);

            final var descricao =
                    (String) Objects.requireNonNull(
                            BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), descricaoField)
                    ).getReadMethod().invoke(objetoValidacao);

            if (valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null) {
                valido = descricao.toLowerCase().contains(this.descricaoObrigatoria.toLowerCase());
            }

            return valido;
        } catch (Exception e) {
            throw new ValidationException(e);
        }
    }

}