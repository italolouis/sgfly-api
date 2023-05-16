package br.com.sgfly.controller;

import br.com.sgfly.model.DadosDespesa;
import br.com.sgfly.model.enums.CategoriaEnum;
import br.com.sgfly.model.enums.PeriodicidadeEnum;
import br.com.sgfly.service.DespesaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
        despesaService.incluirDespesa(dados);
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaEnum>> getCategorias() {
        return new ResponseEntity<List<CategoriaEnum>>(
                Arrays.asList(CategoriaEnum.values()), HttpStatus.OK);
    }

}
