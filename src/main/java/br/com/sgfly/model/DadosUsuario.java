package br.com.sgfly.model;

import br.com.sgfly.model.entities.PlanoContas;
import br.com.sgfly.model.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

public record DadosUsuario(

        Long id,
        @NotBlank
        @CPF
        @CNPJ
        String cpfCnpj,
        @NotBlank
        String nome,
        @NotBlank
        String login,
        @NotBlank
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
