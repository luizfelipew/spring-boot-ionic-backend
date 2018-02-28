package com.luizfelipe.cursomc.resources.exception;


import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StandartError implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer status;
    private String msg;
    private Long timeStamp;

}
