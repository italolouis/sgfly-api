package br.com.sgfly.controller;

import br.com.sgfly.model.graph.Graph;
import br.com.sgfly.service.impl.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("services/graph")
public class GraphController {

    @Autowired
    GraphService graphService;


    @GetMapping("/despesasReceitasByPlan")
    public ResponseEntity<List<Graph>> getDespesasReceitasByPlan(@RequestParam(required=false) Long planoId) {

        return new ResponseEntity(graphService.getPeriodosByPlan(planoId), HttpStatus.OK);
    }

}
