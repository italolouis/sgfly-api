package br.com.sgfly.controller;

import br.com.sgfly.model.DadosUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestBody DadosUsuario dadosUsuario){
        return "login";
    }
}
