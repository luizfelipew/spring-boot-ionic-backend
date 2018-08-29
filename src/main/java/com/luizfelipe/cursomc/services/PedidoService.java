package com.luizfelipe.cursomc.services;

import com.luizfelipe.cursomc.domain.ItemPedido;
import com.luizfelipe.cursomc.domain.PagamentoComBoleto;
import com.luizfelipe.cursomc.domain.Pedido;
import com.luizfelipe.cursomc.domain.enums.EstadoPagamento;
import com.luizfelipe.cursomc.repositories.ItemPedidoRepository;
import com.luizfelipe.cursomc.repositories.PagamentoRepository;
import com.luizfelipe.cursomc.repositories.PedidoRepository;
import com.luizfelipe.cursomc.repositories.ProdutoRepository;
import com.luizfelipe.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;


    public Pedido find(Integer id){
        Pedido pedido = pedidoRepository.findOne(id);
        if (pedido == null){
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " , Tipo: " + Pedido.class.getName());
        }
        return pedido;

    }

    public Pedido insert(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstate(new Date());
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        if (pedido.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstate());
        }
        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());

        for (ItemPedido itemPedido: pedido.getItens()){
            itemPedido.setDesconto(0.0);
            itemPedido.setPreco(produtoRepository.findOne(itemPedido.getProduto().getId()).getPreco());
            itemPedido.setPedido(pedido);
        }
        itemPedidoRepository.save(pedido.getItens());
        return pedido;
    }
}
