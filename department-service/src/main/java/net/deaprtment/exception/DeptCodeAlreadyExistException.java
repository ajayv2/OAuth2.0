package net.deaprtment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DeptCodeAlreadyExistException extends RuntimeException{
    private String message;
    private String deptCode;

    public DeptCodeAlreadyExistException(String message,String deptCode){
        super(String.format("%s for this department code '%s'",message,deptCode));
        this.message = message;
        this.deptCode = deptCode;
    }
}
