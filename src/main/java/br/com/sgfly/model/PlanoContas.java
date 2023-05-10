package br.com.sgfly.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "sg_plano_contas")
@Entity(name = "PlanoContas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PlanoContas {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
