package br.com.williamsbarriquero.algafood.domain.repository;

import br.com.williamsbarriquero.algafood.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
}