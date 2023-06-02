package br.com.sgfly.model;

import br.com.sgfly.model.entities.Receita;
import br.com.sgfly.model.enums.CategoriaEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosReceita(
        Long id,
        String descricao,
        Integer valor,
        DadosPlanoContas planoContas,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime dataCadastro,
        CategoriaEnum categoria,
        String observacao,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime dataRecebimento,
        Boolean recebido

) {
    public DadosReceita(Receita receita){
        this(   receita.getId(),
                receita.getDescricao(),
                receita.getValor(),
                new DadosPlanoContas(receita.getPlanoContas()),
                receita.getDataCadastro(),
                receita.getCategoria(),
                receita.getObservacao(),
                receita.getDataRecebimento(),
                receita.getRecebido()
        );
    }
}

