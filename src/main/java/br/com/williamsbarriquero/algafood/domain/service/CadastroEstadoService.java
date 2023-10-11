package br.com.williamsbarriquero.algafood.domain.service;

import br.com.williamsbarriquero.algafood.domain.exception.EntidadeEmUsoException;
import br.com.williamsbarriquero.algafood.domain.exception.EstadoNaoEncontradoException;
import br.com.williamsbarriquero.algafood.domain.model.Estado;
import br.com.williamsbarriquero.algafood.domain.repository.EstadoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroEstadoService {

    private static final String MSG_ESTADO_EM_USO =
            "Estado de código %d não pode ser removido, pois está em uso";

    private final EstadoRepository estadoRepository;

    public CadastroEstadoService(final EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Transactional
    public Estado salvar(final Estado estado) {
        return estadoRepository.save(estado);
    }

    @Transactional
    public void excluir(final Long estadoId) {
        try {
            estadoRepository.deleteById(estadoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(estadoId);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_ESTADO_EM_USO, estadoId));
        }
    }

    public Estado buscarOuFalhar(final Long estadoId) {
        return estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
    }
}
