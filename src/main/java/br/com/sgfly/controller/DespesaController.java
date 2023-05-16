package br.com.sgfly.controller;

import br.com.sgfly.model.DadosDespesa;
import br.com.sgfly.model.DadosPlanoContas;
import br.com.sgfly.model.enums.CategoriaEnum;
import br.com.sgfly.model.enums.PeriodicidadeEnum;
import br.com.sgfly.service.DespesaService;
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
@RequestMapping("services/despesa")
public class DespesaController {
    @Autowired
    DespesaService despesaService;

    @PostMapping
    @Transactional
    public void incluirDespesa(@RequestBody DadosDespesa dados) {
        //Cadastrar despesa
        despesaService.incluirDespesa(dados);
    }

    @GetMapping
    public Page<DadosDespesa> buscarDespesas(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pageable) {
        return despesaService.buscarDespesas(pageable);
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaEnum>> getCategorias() {
        return new ResponseEntity<List<CategoriaEnum>>(
                Arrays.asList(CategoriaEnum.values()), HttpStatus.OK);
    }

}
