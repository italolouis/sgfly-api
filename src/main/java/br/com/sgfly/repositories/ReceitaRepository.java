package br.com.sgfly.repositories;

import br.com.sgfly.model.entities.Despesa;
import br.com.sgfly.model.entities.Receita;
import br.com.sgfly.model.enums.CategoriaEnum;
import br.com.sgfly.model.enums.StatusEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    @Query("SELECT rec FROM Receita rec WHERE"
            + " (:descricao IS NULL OR UPPER(rec.descricao) LIKE :descricao ) AND"
            + " (:dataInicial IS NULL OR (rec.dataRecebimento between :dataInicial and :dataFinal ) ) AND"
            + " (:planoId IS NULL OR rec.planoContas.id = :planoId) AND"
            + " (rec.cliente.id = :clienteId) AND"
            + " (rec.status  = :status)"
            + " ORDER BY rec.dataRecebimento desc")
    List<Receita> findReceitasGeral(
            @Param("descricao") String descricao,
            @Param("dataInicial") LocalDate dataInicial,
            @Param("dataFinal") LocalDate dataFinal,
            @Param("planoId") Long planoId,
            @Param("clienteId") Long clienteId,
            @Param("status") StatusEnum status,
            Pageable pageable);

    @Query("SELECT SUM(r.valor) FROM Receita r " +
            "WHERE r.dataRecebimento BETWEEN :dataInicial AND :dataFinal AND"
            + " (r.cliente.id = :clienteId) AND"
            + " (r.planoContas.id = :planoId)")
    BigDecimal sumReceitasByPeriod(
            @Param("planoId") Long planoId,
            @Param("dataInicial") LocalDate dataInicial,
            @Param("dataFinal") LocalDate dataFinal,
            @Param("clienteId") Long clienteId);
}
