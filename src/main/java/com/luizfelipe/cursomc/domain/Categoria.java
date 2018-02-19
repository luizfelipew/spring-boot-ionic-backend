package com.luizfelipe.cursomc.domain;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode não precisa por causa do @Data
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;

}
