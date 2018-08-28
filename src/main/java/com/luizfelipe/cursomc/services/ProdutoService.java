package com.luizfelipe.cursomc.services;

import com.luizfelipe.cursomc.domain.Categoria;
import com.luizfelipe.cursomc.domain.Produto;
import com.luizfelipe.cursomc.repositories.CategoriaRepository;
import com.luizfelipe.cursomc.repositories.ProdutoRepository;
import com.luizfelipe.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Integer id){
        Produto produto = produtoRepository.findOne(id);
        if (produto == null){
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " , Tipo: " + Produto.class.getName());
        }
        return produto;

    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAll(ids);
        return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);

    }

}
