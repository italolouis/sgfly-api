package br.com.sgfly.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("services/planoContas")
public class PlanoContasController {

    @GetMapping
    public String olaMundo(){
        return "Hello World Spring!";
    }
}
