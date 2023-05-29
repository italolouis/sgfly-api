package br.com.sgfly.service;

import br.com.sgfly.model.DadosUsuario;
import br.com.sgfly.model.entities.Usuario;
import br.com.sgfly.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public void incluirUsuario(DadosUsuario dadosUsuario) {
        Usuario usuario = new Usuario(dadosUsuario);

        LocalDateTime datetime = LocalDateTime.now();
        usuario.setDataCadastro(datetime);

        usuarioRepository.save(usuario);
    }
}
