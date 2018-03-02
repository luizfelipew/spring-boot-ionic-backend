package com.luizfelipe.cursomc.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;
}
