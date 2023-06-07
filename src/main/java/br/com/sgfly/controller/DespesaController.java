package br.com.sgfly.controller;

import br.com.sgfly.model.DadosDespesa;
import br.com.sgfly.model.enums.CategoriaEnum;
import br.com.sgfly.service.impl.DespesaService;
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
    public Page<DadosDespesa> buscarDespesas(@RequestParam(required=false) String descricao,
                                             @RequestParam(required = false) @DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss") LocalDateTime dataInicial,
                                             @RequestParam(required = false) @DateTimeFormat(pattern="dd/MM/yyyy HH:mm:ss") LocalDateTime dataFinal,
                                             @RequestParam(required=false) Long planoId,
                                             @RequestParam(required=false) String categoria,
                                             @PageableDefault(size = 8, page = 0, sort = {"dataVencimento"},
                                                     direction = Sort.Direction.ASC) Pageable pageable) {
        return despesaService.buscarDespesas(descricao, dataInicial, dataFinal, planoId, categoria, pageable);
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaEnum>> getCategorias() {
        return new ResponseEntity<List<CategoriaEnum>>(
                Arrays.asList(CategoriaEnum.values()), HttpStatus.OK);
    }

}
