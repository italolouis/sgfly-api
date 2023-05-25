package br.com.sgfly.model.entities;

import br.com.sgfly.model.DadosUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "sg_usuario")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Usuario {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cpf_cnpj", nullable = false, unique = true)
    private String cpfCnpj;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "login", nullable = false, unique = true)
    private String login;
    @Column(name = "senha", nullable = false)
    private String senha;
    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    public Usuario(DadosUsuario dadosUsuario){
        this.id = dadosUsuario.id();
        this.cpfCnpj = dadosUsuario.cfpCnpj();
        this.nome = dadosUsuario.nome();
        this.login = dadosUsuario.login();
        this.senha = dadosUsuario.senha();
        this.dataCadastro = dadosUsuario.dataCadastro();
    }
}