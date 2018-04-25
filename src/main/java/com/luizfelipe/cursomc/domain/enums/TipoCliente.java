package com.luizfelipe.cursomc.domain.enums;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");


    private Integer cod;
    private String descricao;


    public static TipoCliente toEnum(Integer cod){

        if (cod == null){
            return null;
        }

        for (TipoCliente x : TipoCliente.values()){
            if (cod.equals(x.getCod())){
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);

    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
