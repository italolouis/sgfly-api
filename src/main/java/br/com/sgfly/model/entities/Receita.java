package br.com.sgfly.model.entities;

import br.com.sgfly.model.DadosDespesa;
import br.com.sgfly.model.DadosReceita;
import br.com.sgfly.model.converter.BooleanConverter;
import br.com.sgfly.model.converter.CategoriaConverter;
import br.com.sgfly.model.enums.CategoriaEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "sg_receita")
@Entity(name = "Receita")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Receita {
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

    @Column(name = "data_recebimento", nullable = false)
    private LocalDateTime dataRecebimento;

    @Column(name = "recebido")
    @Convert(converter = BooleanConverter.class)
    private Boolean recebido;

    public Receita(DadosReceita dadosReceita){
        this.id = dadosReceita.id();
        this.descricao = dadosReceita.descricao();
        this.valor = dadosReceita.valor();
        this.planoContas = new PlanoContas(dadosReceita.planoContas());
        this.dataCadastro = dadosReceita.dataCadastro();
        this.categoria = dadosReceita.categoria();
        this.observacao = dadosReceita.observacao();
        this.dataRecebimento = dadosReceita.dataRecebimento();
        this.recebido = dadosReceita.recebido();
    }
}