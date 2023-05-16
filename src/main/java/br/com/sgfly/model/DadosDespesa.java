package br.com.sgfly.model;

import br.com.sgfly.model.entities.Despesa;
import br.com.sgfly.model.enums.CategoriaEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosDespesa(
        Long id,
        String descricao,
        Integer valor,
        DadosPlanoContas planoContas,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime dataCadastro,
        CategoriaEnum categoria,
        String observacao,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime dataVencimento,
        Boolean pago

) {
    public DadosDespesa(Despesa despesa){
        this(   despesa.getId(),
                despesa.getDescricao(),
                despesa.getValor(),
                new DadosPlanoContas(despesa.getPlanoContas()),
                despesa.getDataCadastro(),
                despesa.getCategoria(),
                despesa.getObservacao(),
                despesa.getDataVencimento(),
                despesa.getPago()
        );
    }
}

