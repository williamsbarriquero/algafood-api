package br.com.williamsbarriquero.algafood.domain.repository;

import br.com.williamsbarriquero.algafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
}
