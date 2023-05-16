package br.com.sgfly.model;

import br.com.sgfly.model.entities.PlanoContas;
import br.com.sgfly.model.enums.PeriodicidadeEnum;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Date;

public record DadosPlanoContas(
        Long id,
        @NotBlank
        String descricao,

        @NotBlank
        Boolean padrao,

        @NotBlank
        PeriodicidadeEnum periodicidade,

        @NotBlank
        LocalDateTime dataInicio,

        @NotBlank
        LocalDateTime dataFim
) {
        public DadosPlanoContas(PlanoContas planoContas){
                this(   planoContas.getId(),
                        planoContas.getDescricao(),
                        planoContas.getPadrao(),
                        planoContas.getPeriodicidade(),
                        planoContas.getDataInicio(),
                        planoContas.getDataFim());
        }
}