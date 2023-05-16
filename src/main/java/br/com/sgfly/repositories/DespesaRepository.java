package br.com.sgfly.repositories;

import br.com.sgfly.model.entities.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
}
