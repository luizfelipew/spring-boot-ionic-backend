package com.luizfelipe.cursomc.repositories;

import com.luizfelipe.cursomc.domain.Categoria;
import com.luizfelipe.cursomc.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

    /*@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
    // aceita dessa forma tb o nome do método poruqe a Query sobrescreve de qualquer forma
    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);*/

    // Colocando no padrão do spring data JPA
    @Transactional(readOnly = true)
    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
}
