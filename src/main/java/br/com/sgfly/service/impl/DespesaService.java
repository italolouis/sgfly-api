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
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
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

        //TODO - retirar quando implementar funcionalidade de fixo
        despesa.setFixo(Boolean.FALSE);
        despesa.setDataCadastro(LocalDateTime.now());
        despesa.setCliente(authenticationService.getLoggedUser());
        despesa.setStatus(StatusEnum.ATIVO);

        if(dadosDespesa.planoContas().id() != null){
            Optional<PlanoContas> planoContas = planoContasRepository.findById(dadosDespesa.planoContas().id());
            despesa.setPlanoContas(planoContas.get());
        }
        despesaRepository.save(despesa);
    }

    public void atualizarDespesa(DadosDespesa dadosDespesa) {
        Despesa despesaEntity = despesaRepository.findById(dadosDespesa.id()).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Despesa não encontrada!"));

        despesaEntity.setCategoria(dadosDespesa.categoria());
        despesaEntity.setObservacao(dadosDespesa.observacao());
        despesaEntity.setDataVencimento(dadosDespesa.dataVencimento());
        despesaEntity.setDescricao(dadosDespesa.descricao());
        despesaEntity.setValor(dadosDespesa.valor());
        despesaRepository.save(despesaEntity);
    }

    public void deletarDespesa(Long id) throws ServiceException {
        try {
            Despesa despesaEntity = despesaRepository.findById(id).orElseThrow(()
                    -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Despesa não encontrada!"));

            despesaEntity.setStatus(StatusEnum.INATIVO);
            despesaRepository.save(despesaEntity);
        } catch (Exception e) {
            throw new ServiceException("Erro: " + e.getMessage());
        }
    }

    public Page<DadosDespesa> buscarDespesas(String descricao, LocalDate dataInicial, LocalDate dataFinal, Long planoId, String categoria, Pageable pageable) {
        Long clienteId = authenticationService.getLoggedUser().getId();

        Page<Despesa> dadosDespesas =  despesaRepository.findDespesasGeral(
                descricao, dataInicial, dataFinal, planoId, CategoriaEnum.parse(categoria),
                 clienteId, StatusEnum.ATIVO, pageable);

        var elements = dadosDespesas.stream().map(despesas ->
                new DadosDespesa(despesas)).collect(Collectors.toList());
        return new PageImpl<>(elements, pageable, dadosDespesas.getTotalElements());
    }

    public BigDecimal getSumDespesasByPeriod(Long planoId, LocalDate dataInicio, LocalDate dataFinal){
        Long clienteId = authenticationService.getLoggedUser().getId();
        return despesaRepository.sumDespesasByPeriod(planoId, dataInicio, dataFinal, clienteId, StatusEnum.ATIVO);
    }
}
