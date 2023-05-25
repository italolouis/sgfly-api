package br.com.sgfly.service;

import br.com.sgfly.model.DadosDespesa;
import br.com.sgfly.model.DadosUsuario;
import br.com.sgfly.model.entities.Despesa;
import br.com.sgfly.model.entities.PlanoContas;
import br.com.sgfly.model.entities.Usuario;
import br.com.sgfly.model.filters.FilterDespesas;
import br.com.sgfly.repositories.DespesaRepository;
import br.com.sgfly.repositories.PlanoContasRepository;
import br.com.sgfly.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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
