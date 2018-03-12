package com.luizfelipe.cursomc.resources;

import com.luizfelipe.cursomc.domain.Cliente;
import com.luizfelipe.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> Find(@PathVariable Integer id) {

        Cliente cliente = clienteService.buscar(id);

        return ResponseEntity.ok().body(cliente);
    }
}
