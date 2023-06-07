package br.com.sgfly.service.impl;

import br.com.sgfly.model.DadosReceita;
import br.com.sgfly.model.entities.PlanoContas;
import br.com.sgfly.model.entities.Receita;
import br.com.sgfly.model.enums.CategoriaEnum;
import br.com.sgfly.model.enums.StatusEnum;
import br.com.sgfly.repositories.PlanoContasRepository;
import br.com.sgfly.repositories.ReceitaRepository;
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
public class ReceitaService {
    @Autowired
    ReceitaRepository receitaRepository;

    @Autowired
    PlanoContasRepository planoContasRepository;

    @Autowired
    AuthenticationService authenticationService;

    public void incluirReceita(DadosReceita dadosReceita) {
        Receita receita = new Receita(dadosReceita);

        LocalDateTime datetime = LocalDateTime.now();
        receita.setDataCadastro(datetime);

        //TODO - retirar quando implementar funcionalidade de fixo
        receita.setFixo(false);
        receita.setPagoRecebido(false);
        receita.setStatus(StatusEnum.ATIVO);

        receita.setCliente(authenticationService.getLoggedUser());

        if(dadosReceita.planoContas().id() != null){
            Optional<PlanoContas> planoContas = planoContasRepository.findById(dadosReceita.planoContas().id());
            receita.setPlanoContas(planoContas.get());
        }

        receitaRepository.save(receita);
    }

    public Page<DadosReceita> buscarReceitas(String descricao, LocalDateTime dataInicial, LocalDateTime dataFinal, Long planoId, Pageable pageable) {
        Long clienteId = authenticationService.getLoggedUser().getId();
        List<Receita> dadosReceita =  receitaRepository.findReceitasGeral(
                descricao, dataInicial, dataFinal, planoId, clienteId, StatusEnum.ATIVO, pageable);

        var elements = dadosReceita.stream().map(receita -> new DadosReceita(receita)).collect(Collectors.toList());
        return new PageImpl<>(elements);
    }
}
