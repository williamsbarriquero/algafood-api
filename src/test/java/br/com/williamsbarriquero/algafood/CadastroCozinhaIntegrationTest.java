package br.com.williamsbarriquero.algafood;

import br.com.williamsbarriquero.algafood.domain.exception.CozinhaNaoEncontradaException;
import br.com.williamsbarriquero.algafood.domain.exception.EntidadeEmUsoException;
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
    void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
        var novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");

        novaCozinha = cadastroCozinha.salvar(novaCozinha);

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

    @Test
    void deveFalhar_QuandoExcluirCozinhaEmUso() {
        final var erroEsperado =
                assertThrows(EntidadeEmUsoException.class, () -> cadastroCozinha.excluir(1L));

        assertThat(erroEsperado).isNotNull();
    }

    @Test
    void deveFalhar_QuandoExcluirCozinhaInexistente() {
        final var erroEsperado =
                assertThrows(CozinhaNaoEncontradaException.class, () -> cadastroCozinha.excluir(100L));

        assertThat(erroEsperado).isNotNull();
    }
}
