package br.com.sgfly.model.filters;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record FilterDespesas(
        Long planoId,
        String descricao,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime dataVencimento
) {

}
