package net.orgranization.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OrgCodeAlreadyExistException extends RuntimeException{
    private String message;
    private String organizationCode;
    public OrgCodeAlreadyExistException(String message,String organizationCode){
        super(String.format("%s '%s' already exist",message,organizationCode));
        this.message = message;
        this.organizationCode = organizationCode;
    }
}
