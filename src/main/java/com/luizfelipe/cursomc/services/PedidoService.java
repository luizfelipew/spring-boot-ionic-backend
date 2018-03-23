package com.luizfelipe.cursomc.services;

import com.luizfelipe.cursomc.domain.Pedido;
import com.luizfelipe.cursomc.repositories.PedidoRepository;
import com.luizfelipe.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido find(Integer id){
        Pedido pedido = pedidoRepository.findOne(id);
        if (pedido == null){
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " , Tipo: " + Pedido.class.getName());
        }
        return pedido;

    }

}
