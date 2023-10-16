package br.com.williamsbarriquero.algafood.core.jackson;

import br.com.williamsbarriquero.algafood.api.model.mixin.CidadeMixin;
import br.com.williamsbarriquero.algafood.api.model.mixin.CozinhaMixin;
import br.com.williamsbarriquero.algafood.domain.model.Cidade;
import br.com.williamsbarriquero.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

import java.io.Serial;

@Component
public class JacksonMixinModule extends SimpleModule {

    @Serial
    private static final long serialVersionUID = 2667353240957728438L;

    public JacksonMixinModule() {
        setMixInAnnotation(Cidade.class, CidadeMixin.class);
        setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
    }
}
