package br.com.williamsbarriquero.algafood.core.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {

    @Bean
    public LocalValidatorFactoryBean validator(final MessageSource messageSource) {
        final var factoryBean = new LocalValidatorFactoryBean();
        factoryBean.setValidationMessageSource(messageSource);

        return factoryBean;
    }
}
