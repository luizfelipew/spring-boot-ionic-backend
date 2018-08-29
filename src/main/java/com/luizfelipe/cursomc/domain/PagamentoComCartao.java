package com.luizfelipe.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.luizfelipe.cursomc.domain.enums.EstadoPagamento;
import lombok.*;

import javax.persistence.Entity;

//@Data
//@Builder
@ToString
@Entity
//@NoArgsConstructor
//@AllArgsConstructor
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento{

    private static final long serialVersionUID = 1L;

    private Integer numeroDeParcelas;

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public PagamentoComCartao(){ }

    public PagamentoComCartao( EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }



}
