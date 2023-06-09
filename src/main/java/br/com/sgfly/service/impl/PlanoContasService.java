package br.com.sgfly.service.impl;

import br.com.sgfly.model.DadosDespesa;
import br.com.sgfly.model.DadosPlanoContas;
import br.com.sgfly.model.entities.Despesa;
import br.com.sgfly.model.entities.PlanoContas;
import br.com.sgfly.model.enums.CategoriaEnum;
import br.com.sgfly.model.enums.StatusEnum;
import br.com.sgfly.model.graph.Graph;
import br.com.sgfly.repositories.PlanoContasRepository;
import br.com.sgfly.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PlanoContasService {

    @Autowired
    PlanoContasRepository planoContasRepository;

    @Autowired
    AuthenticationService authenticationService;

    public void incluirPlanoConta(DadosPlanoContas dadosPlanoContas) {
        PlanoContas planoContas = new PlanoContas(dadosPlanoContas);

        //Não deve existir mais de um plano padrão
        if(planoContas.getPadrao()){
            atualizaPadraoPlanoContas();
        }

        planoContas.setCliente(authenticationService.getLoggedUser());
        planoContas.setStatus(StatusEnum.ATIVO);
        planoContas.setDataCadastro(LocalDateTime.now());

        planoContasRepository.save(planoContas);
    }

    private void atualizaPadraoPlanoContas() {
        List<PlanoContas> planoContasList = planoContasRepository.findAll();
        planoContasList.stream().filter(PlanoContas::getPadrao).forEach(plano-> {
            plano.setPadrao(Boolean.FALSE);
            planoContasRepository.save(plano);
        });
    }

    public Page<DadosPlanoContas> getPlanos(String descricao, Pageable pageable) {
        Long clienteId = authenticationService.getLoggedUser().getId();

        List<PlanoContas> dadosPlanos =  planoContasRepository.findPlanosContasGeral(
                descricao, clienteId, StatusEnum.ATIVO, pageable);

        var elements = dadosPlanos.stream().map(planoContas -> new DadosPlanoContas(planoContas)).collect(Collectors.toList());
        return new PageImpl<>(elements);
    }

    public ResponseEntity<DadosPlanoContas> getAllPlano() {
        return new ResponseEntity(planoContasRepository.findAll()
                .stream().map(DadosPlanoContas::new), HttpStatus.OK);
    }

    public ResponseEntity<DadosPlanoContas> getPlanoPadrao() {
        return new ResponseEntity(
                planoContasRepository.findPlanoContasByPadrao(Boolean.TRUE)
                        .stream().map(DadosPlanoContas::new).findFirst(), HttpStatus.OK);
    }
}
