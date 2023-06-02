package br.com.sgfly.controller;

import br.com.sgfly.model.DadosUsuario;
import br.com.sgfly.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public void incluirUsuario(@RequestBody DadosUsuario dados) {
        //Cadastrar usuário
        usuarioService.incluirUsuario(dados);
    }

}
