package me.aluga.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AluguelNotFoundException extends RuntimeException { 
    private static final long serialVersionUID = 1L;
    private String message;
    public AluguelNotFoundException( String message) {
        this.message = message;
    }
}