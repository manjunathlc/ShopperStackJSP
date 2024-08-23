package edu.jsp.ShoperStack.exception;

import edu.jsp.ShoperStack.util.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleProductNotFoundException(ProductNotFoundException exception){
        ResponseStructure<String > structure=new ResponseStructure<String>();
        structure.setMessage(exception.getMessage());
        structure.setData("");
        return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.BAD_REQUEST);
    }
}
