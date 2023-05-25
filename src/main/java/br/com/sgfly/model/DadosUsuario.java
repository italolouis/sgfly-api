package br.com.sgfly.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosUsuario(
        Long id,
        String cfpCnpj,
        String nome,
        String login,
        String usuario,
        String senha,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime dataCadastro
) {
}
