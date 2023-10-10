package br.com.williamsbarriquero.algafood;

import br.com.williamsbarriquero.algafood.domain.model.Cozinha;
import br.com.williamsbarriquero.algafood.domain.service.CadastroCozinhaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CadastroCozinhaIntegrationTest {

    @Autowired
    private CadastroCozinhaService cadastroCozinha;


    @Test
    void testarCadastroCozinhaComSucesso() {
        // cenário
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");

        // ação
        novaCozinha = cadastroCozinha.salvar(novaCozinha);

        // validação
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test
    void testarCadastroCozinhaSemNome() {
        final var novaCozinha = new Cozinha();
        novaCozinha.setNome(null);

        final var erroEsperado =
                assertThrows(ConstraintViolationException.class, () -> cadastroCozinha.salvar(novaCozinha));

        assertThat(erroEsperado).isNotNull();
    }


}
