package com.luizfelipe.cursomc.resources.exception;

import com.luizfelipe.cursomc.services.exceptions.DataIntregityException;
import com.luizfelipe.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
// isso tudo eh padrão do @ControllerAdvice

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){

        StandartError standartError = new StandartError(HttpStatus.NOT_FOUND.value() , e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standartError);
    }

    @ExceptionHandler(DataIntregityException.class)
    public ResponseEntity<StandartError> dataIntegrity(DataIntregityException e, HttpServletRequest request){

        StandartError standartError = new StandartError(HttpStatus.BAD_REQUEST.value() , e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standartError);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandartError> validation(MethodArgumentNotValidException e, HttpServletRequest request){

        ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Error de validação", System.currentTimeMillis());

        for (FieldError x : e.getBindingResult().getFieldErrors()){
            validationError.addError(x.getField(), x.getDefaultMessage());

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }

}
