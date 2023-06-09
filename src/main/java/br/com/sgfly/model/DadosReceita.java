package br.com.sgfly.model;

import br.com.sgfly.model.entities.Receita;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosReceita(
        Long id,
        String descricao,
        Boolean fixo,
        BigDecimal valor,
        DadosPlanoContas planoContas,
        String observacao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataRecebimento,
        Boolean recebido

) {
    public DadosReceita(Receita receita){
        this(   receita.getId(),
                receita.getDescricao(),
                receita.getFixo(),
                receita.getValor(),
                new DadosPlanoContas(receita.getPlanoContas()),
                receita.getObservacao(),
                receita.getDataRecebimento(),
                receita.getPagoRecebido()
        );
    }
}

