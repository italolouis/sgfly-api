package br.com.sgfly.model.entities;

import br.com.sgfly.model.converter.BooleanConverter;
import br.com.sgfly.model.converter.CategoriaConverter;
import br.com.sgfly.model.converter.StatusConverter;
import br.com.sgfly.model.enums.CategoriaEnum;
import br.com.sgfly.model.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "sg_lancamento")
@Entity(name = "Lancamento")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Lancamento {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "plano_id", nullable = false)
    private PlanoContas planoContas;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "fixo")
    @Convert(converter = BooleanConverter.class)
    private Boolean fixo;

    @Column(name = "valor", nullable = false)
    private Integer valor;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "pago_recebido")
    @Convert(converter = BooleanConverter.class)
    private Boolean pagoRecebido;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    @Convert(converter = StatusConverter.class)
    private StatusEnum status;
}