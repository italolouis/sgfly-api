package br.com.sgfly.service;

import br.com.sgfly.model.DadosDespesa;
import br.com.sgfly.model.DadosPlanoContas;
import br.com.sgfly.model.entities.Despesa;
import br.com.sgfly.model.entities.PlanoContas;
import br.com.sgfly.model.enums.StatusEnum;
import br.com.sgfly.repositories.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DespesaService {
    @Autowired
    DespesaRepository despesaRepository;

    public void incluirDespesa(DadosDespesa dadosDespesa) {
        Despesa despesa = new Despesa(dadosDespesa);
        despesaRepository.save(despesa);
    }
}
