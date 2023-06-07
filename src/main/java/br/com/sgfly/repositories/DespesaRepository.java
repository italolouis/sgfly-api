package br.com.sgfly.repositories;

import br.com.sgfly.model.entities.Despesa;
import br.com.sgfly.model.enums.CategoriaEnum;
import br.com.sgfly.model.enums.StatusEnum;
import jdk.jshell.Snippet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    @Query("SELECT desp FROM Despesa desp WHERE"
            + " (:descricao IS NULL OR UPPER(desp.descricao) LIKE :descricao ) AND"
            + " (:dataInicial IS NULL OR (desp.dataVencimento between :dataInicial and :dataFinal ) ) AND"
            + " (:planoId IS NULL OR desp.planoContas.id = :planoId) AND"
            + " (:categoria IS NULL OR desp.categoria = :categoria) AND"
            + " (desp.cliente.id = :clienteId) AND"
            + " (desp.status = :status)"
            + " ORDER BY desp.dataVencimento desc")
    List<Despesa> findDespesasGeral(
            @Param("descricao") String descricao,
            @Param("dataInicial") LocalDateTime dataInicial,
            @Param("dataFinal") LocalDateTime dataFinal,
            @Param("planoId") Long planoId,
            @Param("categoria") CategoriaEnum categoria,
            @Param("clienteId") Long clienteId,
            @Param("status") StatusEnum status,
            Pageable pageable);
}
