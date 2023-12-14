package net.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistException extends RuntimeException{
    private String message;
    private String email;
    public EmailAlreadyExistException(String message,String email){
        super(String.format("%s for this emailid '%s'",message,email));
        this.message = message;
        this.email = email;
    }
}
