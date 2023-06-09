package br.com.sgfly.service.impl;

import br.com.sgfly.model.entities.PlanoContas;
import br.com.sgfly.model.graph.Graph;
import br.com.sgfly.model.graph.SerieItem;
import br.com.sgfly.repositories.DespesaRepository;
import br.com.sgfly.repositories.PlanoContasRepository;
import br.com.sgfly.repositories.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.sgfly.utils.DateUtil.*;

@Service
public class GraphService {

    @Autowired
    private PlanoContasRepository planoContasRepository;

    @Autowired
    private DespesaService despesaService;

    @Autowired
    private ReceitaService receitaService;

    public List<Graph> getPeriodosByPlan(Long planId) {
        List<Graph> listDataGraph = new ArrayList<>();

        Optional<PlanoContas> planoContas = planoContasRepository.findById(planId);
        if(planoContas.isPresent()){
            PlanoContas plano = planoContas.get();

            switch (plano.getPeriodicidade()){
                case MENSAL :
                    List<YearMonth> monthsBetween = getMonthsBetween(plano.getDataInicio(), plano.getDataFim());

                    for (YearMonth yearMonth : monthsBetween) {
                        LocalDate dataInicio = getInitialDate(yearMonth);
                        LocalDate dataFim = getEndDate(yearMonth);

                        Graph graph = new Graph();
                        graph.setName(yearMonth.toString());

                        List<SerieItem> serieItemList = new ArrayList<>();
                        SerieItem serieItem = new SerieItem();

                        serieItem.setName("DESPESA");
                        serieItem.setValue(despesaService.getSumDespesasByPeriod(plano.getId(), dataInicio, dataFim));
                        serieItemList.add(serieItem);

                        serieItem = new SerieItem();
                        serieItem.setName("RECEITA");
                        serieItem.setValue(receitaService.getSumReceitasByPeriod(plano.getId(), dataInicio, dataFim));
                        serieItemList.add(serieItem);

                        graph.setSeries(serieItemList);
                        listDataGraph.add(graph);
                    }
                    break;
            }
        }

        return listDataGraph;
    }
}
