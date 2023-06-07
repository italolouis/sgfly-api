package br.com.sgfly.model.entities;

import br.com.sgfly.model.DadosReceita;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "Receita")
@DiscriminatorValue("RECEITA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Receita extends Lancamento{
    @Column(name = "data_recebimento", nullable = false)
    private LocalDate dataRecebimento;

    public Receita(DadosReceita dadosReceita){
        this.setId(dadosReceita.id());
        this.setDescricao(dadosReceita.descricao());
        this.setPlanoContas(new PlanoContas(dadosReceita.planoContas()));
        this.setFixo(dadosReceita.fixo());
        this.setValor(dadosReceita.valor());
        this.setDataRecebimento(dadosReceita.dataRecebimento());
        this.setObservacao(dadosReceita.observacao());
    }
}