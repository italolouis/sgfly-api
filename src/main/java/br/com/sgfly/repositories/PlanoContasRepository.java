package br.com.sgfly.repositories;

import br.com.sgfly.model.entities.Despesa;
import br.com.sgfly.model.entities.PlanoContas;
import br.com.sgfly.model.enums.CategoriaEnum;
import br.com.sgfly.model.enums.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PlanoContasRepository extends JpaRepository<PlanoContas, Long> {

    @Query("SELECT pc FROM PlanoContas pc WHERE"
            + " (:descricao IS NULL OR UPPER(pc.descricao) LIKE :descricao ) AND"
            + " (pc.cliente.id = :clienteId) AND"
            + " (pc.status = :status)"
            + " ORDER BY pc.dataCadastro ASC")
    Page<PlanoContas> findPlanosContasGeral(
            @Param("descricao") String descricao,
            @Param("clienteId") Long clienteId,
            @Param("status") StatusEnum status,
            Pageable pageable);

    List<PlanoContas> findPlanoContasByPadrao(Boolean padrao);

}