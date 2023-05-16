package br.com.sgfly.model.entities;

import br.com.sgfly.model.DadosDespesa;
import br.com.sgfly.model.converter.BooleanConverter;
import br.com.sgfly.model.converter.CategoriaConverter;
import br.com.sgfly.model.converter.PeriodicidadeConverter;
import br.com.sgfly.model.enums.CategoriaEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "sg_despesa")
@Entity(name = "ContasPagar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Despesa {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "valor", nullable = false)
    private Integer valor;

    @ManyToOne
    @JoinColumn(name = "plano_id", nullable = false)
    private PlanoContas planoContas;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "categoria", nullable = false)
    @Convert(converter = CategoriaConverter.class)
    private CategoriaEnum categoria;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDateTime dataVencimento;

    @Column(name = "pago")
    @Convert(converter = BooleanConverter.class)
    private Boolean pago;

    public Despesa(DadosDespesa dadosDespesa){
        this.id = dadosDespesa.id();
        this.descricao = dadosDespesa.descricao();
        this.valor = dadosDespesa.valor();
        this.planoContas = new PlanoContas(dadosDespesa.planoContas());
        this.dataCadastro = dadosDespesa.dataCadastro();
        this.categoria = dadosDespesa.categoria();
        this.observacao = dadosDespesa.observacao();
        this.dataVencimento = dadosDespesa.dataVencimento();
        this.pago = dadosDespesa.pago();
    }
}