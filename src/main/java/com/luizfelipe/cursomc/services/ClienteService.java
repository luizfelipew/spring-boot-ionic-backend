package com.luizfelipe.cursomc.services;

import com.luizfelipe.cursomc.domain.Categoria;
import com.luizfelipe.cursomc.domain.Cliente;
import com.luizfelipe.cursomc.dto.CategoriaDTO;
import com.luizfelipe.cursomc.dto.ClienteDTO;
import com.luizfelipe.cursomc.repositories.ClienteRepository;
import com.luizfelipe.cursomc.services.exceptions.DataIntregityException;
import com.luizfelipe.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Integer id){
        Cliente cliente = clienteRepository.findOne(id);
        if (cliente == null){
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " , Tipo: " + Cliente.class.getName());
        }
        return cliente;

    }


    public Cliente update(Cliente cliente){
        Cliente newCliente = find(cliente.getId());
        updateData(newCliente, cliente);
        return clienteRepository.save(newCliente);
    }



    public void delete(Integer id) {
        find(id);
        try {
            clienteRepository.delete(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntregityException("Não é possível excluir há entidades relacionadas");
        }
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO clienteDTO){
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null, null,null,null);
    }

    private void updateData(Cliente newCliente, Cliente cliente) {
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
    }

}
