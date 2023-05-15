package br.com.sgfly.controller;

import br.com.sgfly.model.DadosPlanoContas;
import br.com.sgfly.model.enums.PeriodicidadeEnum;
import br.com.sgfly.service.PlanoContasService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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

    @RequestMapping("/periodicidade")
    public ResponseEntity<List<PeriodicidadeEnum>> days() {
        return new ResponseEntity<List<PeriodicidadeEnum>>(
                Arrays.asList(PeriodicidadeEnum.values()), HttpStatus.OK);
    }
}
