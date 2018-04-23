package com.luizfelipe.cursomc.resources.exception;


import lombok.*;

import java.util.ArrayList;
import java.util.List;


public class ValidationError extends StandartError {

    private static final long serialVersionUID = -5676143165815779039L;

    private List<FieldMessage> errors = new ArrayList<>();


    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
        this.errors = errors;
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
