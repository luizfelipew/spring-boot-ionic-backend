package com.luizfelipe.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.luizfelipe.cursomc.domain.enums.TipoCliente;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Builder
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private Integer tipo;

    @JsonManagedReference
    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones = new HashSet<>();

    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(TipoCliente tipo) {
        this.tipo = tipo.getCod();

    }

    public TipoCliente getTipo() {
        return TipoCliente.toEnum(tipo);
    }

    public Cliente(String nome, String email, String cpfOuCnpj, TipoCliente tipo, List<Endereco> enderecos, Set<String> telefones) {
        super();
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = tipo.getCod();
        this.enderecos = enderecos;
        this.telefones = telefones;
    }
}
