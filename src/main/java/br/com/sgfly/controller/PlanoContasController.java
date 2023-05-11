package br.com.sgfly.controller;

import br.com.sgfly.model.DadosPlanoContas;
import br.com.sgfly.service.PlanoContasService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("services/planoContas")
public class PlanoContasController {

    @Autowired
    PlanoContasService planoContasService;

    @PostMapping
    @Transactional
    public void incluirPlanoContas(@RequestBody DadosPlanoContas dados) {
        planoContasService.incluirPlanoConta(dados);
    }
}
