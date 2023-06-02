package br.com.sgfly.repositories;

import br.com.sgfly.model.entities.Despesa;
import br.com.sgfly.model.entities.Receita;
import br.com.sgfly.model.enums.CategoriaEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    @Query("SELECT rec FROM Receita rec WHERE"
            + " (:descricao IS NULL OR UPPER(rec.descricao) LIKE :descricao ) AND"
            + " (:dataInicial IS NULL OR (rec.dataRecebimento between :dataInicial and :dataFinal ) ) AND"
            + " (:planoId IS NULL OR rec.planoContas.id = :planoId) AND"
            + " (:categoria IS NULL OR rec.categoria = :categoria) "
            + " ORDER BY rec.dataRecebimento desc")
    List<Receita> findReceitasGeral(
            @Param("descricao") String descricao,
            @Param("dataInicial") LocalDateTime dataInicial,
            @Param("dataFinal") LocalDateTime dataFinal,
            @Param("planoId") Long planoId,
            @Param("categoria") CategoriaEnum categoria,
            Pageable pageable);
}