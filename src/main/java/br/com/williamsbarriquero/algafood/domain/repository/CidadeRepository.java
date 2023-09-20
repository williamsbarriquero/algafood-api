package br.com.williamsbarriquero.algafood.domain.repository;

import br.com.williamsbarriquero.algafood.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}