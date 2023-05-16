package br.com.sgfly.model.entities;

import br.com.sgfly.model.DadosPlanoContas;
import br.com.sgfly.model.converter.BooleanConverter;
import br.com.sgfly.model.converter.PeriodicidadeConverter;
import br.com.sgfly.model.converter.StatusConverter;
import br.com.sgfly.model.enums.PeriodicidadeEnum;
import br.com.sgfly.model.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "sg_plano_contas")
@Entity(name = "PlanoContas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PlanoContas {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @Convert(converter = BooleanConverter.class)
    private Boolean padrao;
    @Convert(converter = StatusConverter.class)
    private StatusEnum status;
    @Convert(converter = PeriodicidadeConverter.class)
    private PeriodicidadeEnum periodicidade;
    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = " data_fim", nullable = false)
    private LocalDateTime dataFim;

    public PlanoContas(DadosPlanoContas dados) {
        this.descricao = dados.descricao();
        this.padrao = dados.padrao();
        this.periodicidade = dados.periodicidade();
        this.dataInicio = dados.dataInicio();
        this.dataFim = dados.dataFim();
    }
}
