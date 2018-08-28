package com.luizfelipe.cursomc.resources;

import com.luizfelipe.cursomc.domain.Categoria;
import com.luizfelipe.cursomc.domain.Produto;
import com.luizfelipe.cursomc.dto.CategoriaDTO;
import com.luizfelipe.cursomc.dto.ProdutoDTO;
import com.luizfelipe.cursomc.resources.utils.URL;
import com.luizfelipe.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtosService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> Find(@PathVariable Integer id) {

        Produto produtos = produtosService.find(id);

        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction)
    {

        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> produtos = produtosService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> listDTO = produtos.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(listDTO);
    }
}
