package br.com.sgfly.service;

import br.com.sgfly.model.DadosPlanoContas;
import br.com.sgfly.model.entities.PlanoContas;
import br.com.sgfly.model.enums.StatusEnum;
import br.com.sgfly.repositories.PlanoContasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class PlanoContasService {

    @Autowired
    PlanoContasRepository planoContasRepository;

    public void incluirPlanoConta(DadosPlanoContas dadosPlanoContas) {
        PlanoContas planoContas = new PlanoContas(dadosPlanoContas);
        planoContas.setStatus(StatusEnum.ATIVO);
        planoContasRepository.save(planoContas);
    }

    public Page<DadosPlanoContas> getPlano(Pageable pageable) {
        return planoContasRepository.findAll(pageable).map(DadosPlanoContas::new);
    }

    public Stream<DadosPlanoContas> getAllPlano() {
        return planoContasRepository.findAll().stream().map(DadosPlanoContas::new);
    }
}
