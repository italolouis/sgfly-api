package br.com.sgfly.model.entities;

import br.com.sgfly.model.converter.BooleanConverter;
import br.com.sgfly.model.enums.CategoriaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Table(name = "sg_contas_pagar")
@Entity(name = "ContasPagar")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ContasPagar {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "plano_id")
    private PlanoContas planoContas;

    @Column(name = "data_lancamento", nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = "categoria", nullable = false)
    private CategoriaEnum categoria;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDateTime dataVencimento;

    @Column(name = "pago")
    @Convert(converter = BooleanConverter.class)
    private Boolean pago;
}