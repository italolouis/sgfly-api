package br.com.sgfly.controller;

import br.com.sgfly.model.DadosReceita;
import br.com.sgfly.model.enums.CategoriaEnum;
import br.com.sgfly.service.impl.ReceitaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("services/receita")
public class ReceitaController {
    @Autowired
    ReceitaService receitaService;

    @PostMapping
    @Transactional
    public void incluirReceita(@RequestBody DadosReceita dados) {
        //Cadastrar despesa
        receitaService.incluirReceita(dados);
    }


    @GetMapping
    public Page<DadosReceita> buscarDespesas(@RequestParam(required=false) String descricao,
                                             @RequestParam(required = false) @DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss") LocalDateTime dataInicial,
                                             @RequestParam(required = false) @DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss") LocalDateTime dataFinal,
                                             @RequestParam(required=false) Long planoId,
                                             @PageableDefault(size = 8, page = 0, sort = {"dataRecebimento"},
                                                     direction = Sort.Direction.ASC) Pageable pageable) {
        return receitaService.buscarReceitas(descricao, dataInicial, dataFinal, planoId, pageable);
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaEnum>> getCategorias() {
        return new ResponseEntity<List<CategoriaEnum>>(
                Arrays.asList(CategoriaEnum.values()), HttpStatus.OK);
    }

}
