package br.com.sgfly.controller;

import br.com.sgfly.model.DadosUsuario;
import br.com.sgfly.model.entities.Usuario;
import br.com.sgfly.service.authentication.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authentication")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public DadosUsuario login(@RequestBody DadosUsuario dadosUsuario){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(dadosUsuario.login(), dadosUsuario.senha());
        Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        var usuario = (Usuario) authenticate.getPrincipal();
        String token =  tokenService.gerarToken(usuario);
        usuario.setToken(token);
        return new DadosUsuario(usuario);
    }
}
