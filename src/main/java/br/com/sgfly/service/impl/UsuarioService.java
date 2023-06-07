package br.com.sgfly.service.impl;

import br.com.sgfly.model.DadosUsuario;
import br.com.sgfly.model.entities.Usuario;
import br.com.sgfly.model.enums.StatusEnum;
import br.com.sgfly.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void incluirUsuario(DadosUsuario dadosUsuario) {
        Usuario usuario = new Usuario(dadosUsuario);

        LocalDateTime datetime = LocalDateTime.now();
        usuario.setDataCadastro(datetime);
        usuario.setStatus(StatusEnum.ATIVO);
        usuario.setSenha(passwordEncoder.encode(dadosUsuario.senha()));

        usuarioRepository.save(usuario);
    }

}
