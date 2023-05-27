package br.com.sgfly.model.entities;

import br.com.sgfly.model.DadosUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Table(name = "sg_usuario")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Usuario implements UserDetails {
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
    @Transient
    private String token;

    public Usuario(DadosUsuario dadosUsuario){
        this.id = dadosUsuario.id();
        this.cpfCnpj = dadosUsuario.cfpCnpj();
        this.nome = dadosUsuario.nome();
        this.login = dadosUsuario.login();
        this.senha = dadosUsuario.senha();
        this.dataCadastro = dadosUsuario.dataCadastro();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}