package br.com.williamsbarriquero.algafood.domain.service;

import br.com.williamsbarriquero.algafood.domain.exception.CidadeNaoEncontradaException;
import br.com.williamsbarriquero.algafood.domain.exception.EntidadeEmUsoException;
import br.com.williamsbarriquero.algafood.domain.model.Cidade;
import br.com.williamsbarriquero.algafood.domain.model.Estado;
import br.com.williamsbarriquero.algafood.domain.repository.CidadeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

    private static final String MSG_CIDADE_EM_USO
            = "Cidade de código %d não pode ser removida, pois está em uso";

    private final CidadeRepository cidadeRepository;
    private final CadastroEstadoService cadastroEstado;

    public CadastroCidadeService(CidadeRepository cidadeRepository, CadastroEstadoService cadastroEstado) {
        this.cidadeRepository = cidadeRepository;
        this.cadastroEstado = cadastroEstado;
    }

    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();

        Estado estado = cadastroEstado.buscarOuFalhar(estadoId);

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.deleteById(cidadeId);

        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(cidadeId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_CIDADE_EM_USO, cidadeId));
        }
    }

    public Cidade buscarOuFalhar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
    }
}