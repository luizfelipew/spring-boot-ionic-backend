package com.luizfelipe.cursomc.services.exceptions;


public class DataIntregityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataIntregityException(String msg){
        super(msg);
    }

    public DataIntregityException(String msg, Throwable cause){
        super(msg, cause);
    }

}
