package br.com.sgfly.model.entities;

import br.com.sgfly.model.DadosDespesa;
import br.com.sgfly.model.converter.CategoriaConverter;
import br.com.sgfly.model.enums.CategoriaEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "Despesa")
@DiscriminatorValue("DESPESA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Despesa extends Lancamento{

    @Column(name = "categoria", nullable = false)
    @Convert(converter = CategoriaConverter.class)
    private CategoriaEnum categoria;
    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;
    @Column(name = "cod_barras")
    private String codBarras;

    public Despesa(DadosDespesa dadosDespesa){
        this.setId(dadosDespesa.id());
        this.setDescricao(dadosDespesa.descricao());
        this.setValor(dadosDespesa.valor());
        this.setPlanoContas(new PlanoContas(dadosDespesa.planoContas()));
        this.setObservacao(dadosDespesa.observacao());
        this.setPagoRecebido(dadosDespesa.pago());
        this.dataVencimento = dadosDespesa.dataVencimento();
        this.categoria = dadosDespesa.categoria();
        this.codBarras = dadosDespesa.codBarras();
    }
}