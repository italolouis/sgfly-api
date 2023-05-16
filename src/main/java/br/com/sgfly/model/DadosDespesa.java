package br.com.sgfly.model;

import br.com.sgfly.model.entities.Despesa;
import br.com.sgfly.model.entities.PlanoContas;
import br.com.sgfly.model.enums.CategoriaEnum;
import java.time.LocalDateTime;

public record DadosDespesa(
        Long id,
        String descricao,
        PlanoContas planoContas,
        LocalDateTime data,
        CategoriaEnum categoria,
        String observacao,
        LocalDateTime dataVencimento,
        Boolean pago

) {
    public DadosDespesa(Despesa despesa){
        this(   despesa.getId(),
                despesa.getDescricao(),
                despesa.getPlanoContas(),
                despesa.getData(),
                despesa.getCategoria(),
                despesa.getObservacao(),
                despesa.getDataVencimento(),
                despesa.getPago()
        );
    }
}

