package com.luizfelipe.cursomc.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ItemPedido  implements Serializable {

    private static final long serialVersionUID = 1L;

    private ItemPedidoPK id = new ItemPedidoPK();

    private Double desconto;
    private Integer quantidade;
    private Double preco;


    public ItemPedido(Pedido pedido, Produto produto , Double desconto, Integer quantidade, Double preco) {
        super();
        id.setPedido(pedido);
        id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Pedido getPedido(){
        return id.getPedido();
    }


    public Produto getProduto(){
        return id.getProduto();
    }
}
