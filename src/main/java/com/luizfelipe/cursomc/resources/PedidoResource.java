package com.luizfelipe.cursomc.resources;

import com.luizfelipe.cursomc.domain.Categoria;
import com.luizfelipe.cursomc.domain.Pedido;
import com.luizfelipe.cursomc.dto.CategoriaDTO;
import com.luizfelipe.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido pedido){
        pedido = pedidosService.insert(pedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
