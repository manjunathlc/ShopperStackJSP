package edu.jsp.ShoperStack.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ResponseStructure<T> {
    private int statusCode;
    private String message;
    private T data;
}
