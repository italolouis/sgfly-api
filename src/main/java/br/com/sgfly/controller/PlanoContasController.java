package br.com.sgfly.controller;

import br.com.sgfly.model.DadosPlanoContas;
import br.com.sgfly.model.enums.PeriodicidadeEnum;
import br.com.sgfly.service.impl.PlanoContasService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public Page<DadosPlanoContas> getPlanoContas(@RequestParam(required=false) String descricao,
            @PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pageable) {
        return planoContasService.getPlanos(descricao, pageable);
    }

    @GetMapping("/planoPadrao")
    public ResponseEntity<DadosPlanoContas> getPlanoPadrao() {
        return planoContasService.getPlanoPadrao();
    }

    @GetMapping("/allPlans")
    public ResponseEntity<DadosPlanoContas> getPlanoContas() {
        return planoContasService.getAllPlano();
    }

    @GetMapping("/periodicidade")
    public ResponseEntity<List<PeriodicidadeEnum>> getPeriodicidade() {
        return new ResponseEntity<List<PeriodicidadeEnum>>(
                Arrays.asList(PeriodicidadeEnum.values()), HttpStatus.OK);
    }
}
