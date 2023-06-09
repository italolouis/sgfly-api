package br.com.sgfly.model;

import br.com.sgfly.model.entities.Despesa;
import br.com.sgfly.model.enums.CategoriaEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosDespesa(
        Long id,
        String descricao,
        BigDecimal valor,
        DadosPlanoContas planoContas,
        CategoriaEnum categoria,
        String observacao,
        String codBarras,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataVencimento,
        Boolean pago

) {
    public DadosDespesa(Despesa despesa){
        this(   despesa.getId(),
                despesa.getDescricao(),
                despesa.getValor(),
                new DadosPlanoContas(despesa.getPlanoContas()),
                despesa.getCategoria(),
                despesa.getObservacao(),
                despesa.getCodBarras(),
                despesa.getDataVencimento(),
                despesa.getPagoRecebido()
        );
    }
}

