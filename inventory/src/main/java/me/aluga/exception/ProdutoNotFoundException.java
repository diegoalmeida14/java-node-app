package me.aluga.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProdutoNotFoundException extends RuntimeException { 
    private static final long serialVersionUID = 1L;
    private String message;
    public ProdutoNotFoundException( String message) {
        this.message = message;
    }
}