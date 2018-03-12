package com.luizfelipe.cursomc.services;

import com.luizfelipe.cursomc.domain.Cliente;
import com.luizfelipe.cursomc.repositories.ClienteRepository;
import com.luizfelipe.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscar(Integer id){
        Cliente cliente = clienteRepository.findOne(id);
        if (cliente == null){
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " , Tipo: " + Cliente.class.getName());
        }
        return cliente;

    }

}
