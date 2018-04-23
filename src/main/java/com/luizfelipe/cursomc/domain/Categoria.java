package com.luizfelipe.cursomc.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode não precisa por causa do @Data
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    //@JsonManagedReference
    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos = new ArrayList<>();


    public Categoria(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
