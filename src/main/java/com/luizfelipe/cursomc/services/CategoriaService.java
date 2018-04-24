package com.luizfelipe.cursomc.services;

import com.luizfelipe.cursomc.domain.Categoria;
import com.luizfelipe.cursomc.domain.Cliente;
import com.luizfelipe.cursomc.dto.CategoriaDTO;
import com.luizfelipe.cursomc.repositories.CategoriaRepository;
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
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id){
        Categoria categoria = categoriaRepository.findOne(id);
        if (categoria == null){
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " , Tipo: " + Categoria.class.getName());
        }
        return categoria;

    }

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria categoria){
        Categoria newCategoria = find(categoria.getId());
        updateData(newCategoria, categoria);
        return categoriaRepository.save(newCategoria);
    }


    public void delete(Integer id) {
        find(id);
        try {
            categoriaRepository.delete(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntregityException("Não é possível excluir uma categoria que possui produtos");
        }
    }

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO categoriaDTO){

        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());

    }


    private void updateData(Categoria newCategoria, Categoria categoria) {
        newCategoria.setNome(categoria.getNome());
    }
}
