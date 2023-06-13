package br.com.sgfly.service.impl;

import br.com.sgfly.model.DadosDespesa;
import br.com.sgfly.model.DadosReceita;
import br.com.sgfly.model.entities.Despesa;
import br.com.sgfly.model.entities.PlanoContas;
import br.com.sgfly.model.entities.Receita;
import br.com.sgfly.model.enums.StatusEnum;
import br.com.sgfly.repositories.PlanoContasRepository;
import br.com.sgfly.repositories.ReceitaRepository;
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
public class ReceitaService {
    @Autowired
    ReceitaRepository receitaRepository;

    @Autowired
    PlanoContasRepository planoContasRepository;

    @Autowired
    AuthenticationService authenticationService;

    public void incluirReceita(DadosReceita dadosReceita) {
        Receita receita = new Receita(dadosReceita);

        //TODO - retirar quando implementar funcionalidade de fixo
        receita.setFixo(Boolean.FALSE);
        receita.setPagoRecebido(Boolean.FALSE);
        receita.setStatus(StatusEnum.ATIVO);
        receita.setCliente(authenticationService.getLoggedUser());
        receita.setDataCadastro(LocalDateTime.now());

        if(dadosReceita.planoContas().id() != null){
            Optional<PlanoContas> planoContas = planoContasRepository.findById(dadosReceita.planoContas().id());
            receita.setPlanoContas(planoContas.get());
        }

        receitaRepository.save(receita);
    }

    public void atualizarReceita(DadosReceita dadosReceita) {
        Receita receitaEntity = receitaRepository.findById(dadosReceita.id()).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada!"));

        receitaEntity.setObservacao(dadosReceita.observacao());
        receitaEntity.setDataRecebimento(dadosReceita.dataRecebimento());
        receitaEntity.setDescricao(dadosReceita.descricao());
        receitaEntity.setValor(dadosReceita.valor());
        receitaRepository.save(receitaEntity);
    }

    public void deletarReceita(Long id) throws ServiceException {
        try {
            Receita receitaEntity = receitaRepository.findById(id).orElseThrow(()
                    -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada!"));

            receitaEntity.setStatus(StatusEnum.INATIVO);
            receitaRepository.save(receitaEntity);
        } catch (Exception e) {
            throw new ServiceException("Erro: " + e.getMessage());
        }
    }


    public Page<DadosReceita> buscarReceitas(String descricao, LocalDate dataInicial, LocalDate dataFinal, Long planoId, Pageable pageable) {
        Long clienteId = authenticationService.getLoggedUser().getId();
        Page<Receita> dadosReceita =  receitaRepository.findReceitasGeral(
                descricao, dataInicial, dataFinal, planoId, clienteId, StatusEnum.ATIVO, pageable);

        var elements = dadosReceita.stream().map(receita -> new DadosReceita(receita)).collect(Collectors.toList());
        return new PageImpl<>(elements, pageable, dadosReceita.getTotalElements());
    }

    public BigDecimal getSumReceitasByPeriod(Long planoId, LocalDate dataInicio, LocalDate dataFim){
        Long clienteId = authenticationService.getLoggedUser().getId();
        return receitaRepository.sumReceitasByPeriod(planoId, dataInicio, dataFim, clienteId, StatusEnum.ATIVO);
    }
}
