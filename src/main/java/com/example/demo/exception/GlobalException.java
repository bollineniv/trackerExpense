package com.example.demo.exception;

import com.example.demo.model.ExceptionObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionObject> expenseNotFoundExceptionHandler(ResourceNotFoundException ex,
                                                                           WebRequest web){
        ExceptionObject exceptionObject = new ExceptionObject();

        exceptionObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        exceptionObject.setErrorDescription(ex.getMessage());
        exceptionObject.setTimestamp(new Date());

        return new ResponseEntity<ExceptionObject>(exceptionObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionObject> methodArgumentTypeMismatchHandler(MethodArgumentTypeMismatchException ex
            , WebRequest web){
        ExceptionObject exceptionObject = new ExceptionObject();

        exceptionObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionObject.setErrorDescription(ex.getMessage());
        exceptionObject.setTimestamp(new Date());

        return new ResponseEntity<ExceptionObject>(exceptionObject,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionObject> generalExceptionHandler(Exception ex, WebRequest web){
        ExceptionObject exceptionObject = new ExceptionObject();

        exceptionObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exceptionObject.setErrorDescription((ex.getMessage()));
        exceptionObject.setTimestamp(new Date());

        return new ResponseEntity<ExceptionObject>(exceptionObject,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
                    , HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,Object> errorBody = new HashMap<String,Object>();

        errorBody.put("status",HttpStatus.BAD_REQUEST.value());
        errorBody.put("timestamp",new Date());
        List<String> error = ex.getBindingResult().getFieldErrors()
                .stream().map(x->x.getDefaultMessage()).collect(Collectors.toList());

        errorBody.put("message",error);
        return new ResponseEntity<Object>(errorBody,HttpStatus.BAD_REQUEST);

    }
}
