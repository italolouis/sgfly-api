package br.com.sgfly.model;

import br.com.sgfly.model.entities.PlanoContas;
import br.com.sgfly.model.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosUsuario(
        Long id,
        String cpfCnpj,
        String nome,
        String login,
        String senha,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime dataCadastro,
        String token
) {
        public DadosUsuario(Usuario usuario){
                this(   usuario.getId(),
                        usuario.getCpfCnpj(),
                        usuario.getNome(),
                        usuario.getUsername(),
                        null,
                        usuario.getDataCadastro(),
                        usuario.getToken()
                );
        }
}
