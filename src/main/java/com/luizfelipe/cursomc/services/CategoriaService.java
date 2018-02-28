package com.luizfelipe.cursomc.services;

import com.luizfelipe.cursomc.domain.Categoria;
import com.luizfelipe.cursomc.repositories.CategoriaRepository;
import com.luizfelipe.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscar(Integer id){
        Categoria categoria = categoriaRepository.findOne(id);
        if (categoria == null){
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " , Tipo: " + Categoria.class.getName());
        }
        return categoria;

    }

}
