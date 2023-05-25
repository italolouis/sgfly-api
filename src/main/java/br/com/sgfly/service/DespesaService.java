package br.com.sgfly.service;

import br.com.sgfly.model.DadosDespesa;
import br.com.sgfly.model.entities.Despesa;
import br.com.sgfly.model.entities.PlanoContas;
import br.com.sgfly.model.filters.FilterDespesas;
import br.com.sgfly.repositories.DespesaRepository;
import br.com.sgfly.repositories.PlanoContasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DespesaService {
    @Autowired
    DespesaRepository despesaRepository;

    @Autowired
    PlanoContasRepository planoContasRepository;

    public void incluirDespesa(DadosDespesa dadosDespesa) {
        Despesa despesa = new Despesa(dadosDespesa);

        LocalDateTime datetime = LocalDateTime.now();
        despesa.setDataCadastro(datetime);

        if(dadosDespesa.planoContas().id() != null){
            Optional<PlanoContas> planoContas = planoContasRepository.findById(dadosDespesa.planoContas().id());
            despesa.setPlanoContas(planoContas.get());
        }
        despesaRepository.save(despesa);
    }

    public Page<DadosDespesa> buscarDespesas(FilterDespesas filterDespesas, Pageable pageable) {
        return despesaRepository.findAll(pageable).map(DadosDespesa::new);
    }
}
