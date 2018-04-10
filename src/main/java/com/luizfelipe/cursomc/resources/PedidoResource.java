package com.luizfelipe.cursomc.resources;

import com.luizfelipe.cursomc.domain.Pedido;
import com.luizfelipe.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService pedidosService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> Find(@PathVariable Integer id) {

        Pedido pedidos = pedidosService.find(id);

        return ResponseEntity.ok().body(pedidos);
    }
}