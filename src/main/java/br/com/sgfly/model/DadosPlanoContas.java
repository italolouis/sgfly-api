package br.com.sgfly.model;

import br.com.sgfly.model.enums.PeriodicidadeEnum;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DadosPlanoContas(
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
}