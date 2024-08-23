package edu.jsp.ShoperStack.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartNotFoundException extends RuntimeException{
    private String message;
}
