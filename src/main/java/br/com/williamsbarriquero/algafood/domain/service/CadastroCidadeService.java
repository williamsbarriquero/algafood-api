package br.com.williamsbarriquero.algafood.domain.service;

import br.com.williamsbarriquero.algafood.domain.exception.CidadeNaoEncontradaException;
import br.com.williamsbarriquero.algafood.domain.exception.EntidadeEmUsoException;
import br.com.williamsbarriquero.algafood.domain.model.Cidade;
import br.com.williamsbarriquero.algafood.domain.repository.CidadeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroCidadeService {

    private static final String MSG_CIDADE_EM_USO
            = "Cidade de código %d não pode ser removida, pois está em uso";

    private final CidadeRepository cidadeRepository;
    private final CadastroEstadoService cadastroEstado;

    public CadastroCidadeService(final CidadeRepository cidadeRepository, final CadastroEstadoService cadastroEstado) {
        this.cidadeRepository = cidadeRepository;
        this.cadastroEstado = cadastroEstado;
    }

    @Transactional
    public Cidade salvar(final Cidade cidade) {
        final var estadoId = cidade.getEstado().getId();

        final var estado = cadastroEstado.buscarOuFalhar(estadoId);

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    @Transactional
    public void excluir(final Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);
            cidadeRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(cidadeId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_CIDADE_EM_USO, cidadeId));
        }
    }

    public Cidade buscarOuFalhar(final Long cidadeId) {
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
    }
}