package br.com.sgfly.service.impl;

import br.com.sgfly.model.DadosDespesa;
import br.com.sgfly.model.DadosPlanoContas;
import br.com.sgfly.model.entities.Despesa;
import br.com.sgfly.model.entities.PlanoContas;
import br.com.sgfly.model.enums.CategoriaEnum;
import br.com.sgfly.model.enums.StatusEnum;
import br.com.sgfly.repositories.DespesaRepository;
import br.com.sgfly.repositories.PlanoContasRepository;
import br.com.sgfly.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DespesaService {
    @Autowired
    DespesaRepository despesaRepository;

    @Autowired
    PlanoContasRepository planoContasRepository;

    @Autowired
    AuthenticationService authenticationService;

    public void incluirDespesa(DadosDespesa dadosDespesa) {
        Despesa despesa = new Despesa(dadosDespesa);

        LocalDateTime datetime = LocalDateTime.now();
        despesa.setDataCadastro(datetime);
        despesa.setCliente(authenticationService.getLoggedUser());
        despesa.setStatus(StatusEnum.ATIVO);

        if(dadosDespesa.planoContas().id() != null){
            Optional<PlanoContas> planoContas = planoContasRepository.findById(dadosDespesa.planoContas().id());
            despesa.setPlanoContas(planoContas.get());
        }
        despesaRepository.save(despesa);
    }

    public Page<DadosDespesa> buscarDespesas(String descricao, LocalDateTime dataInicial, LocalDateTime dataFinal, Long planoId, String categoria, Pageable pageable) {
        Long clienteId = authenticationService.getLoggedUser().getId();

        List<Despesa> dadosDespesas =  despesaRepository.findDespesasGeral(
                descricao, dataInicial, dataFinal, planoId, CategoriaEnum.parse(categoria),
                 clienteId, StatusEnum.ATIVO, pageable);

        var elements = dadosDespesas.stream().map(despesas -> new DadosDespesa(despesas)).collect(Collectors.toList());
        return new PageImpl<>(elements);
    }
}
