package com.eshoppingzone.productservice.exception;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.eshoppingzone.productservice.auth.exception.FailedToLoginException;
import com.eshoppingzone.productservice.auth.exception.InvalidTokenException;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(FailedToLoginException.class)
    public @ResponseBody String handleEmployeeNotFoundException(HttpServletRequest request, Exception ex){

        JSONObject obj = new JSONObject();
        obj.put("url", request.getRequestURL().toString());
        obj.put("message", ex.getMessage());
        obj.put("status", UNAUTHORIZED);


        return obj.toJSONString();
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(InvalidTokenException.class)
    public @ResponseBody String handleEmployeeNotFoundException1(HttpServletRequest request, Exception ex){

        JSONObject obj = new JSONObject();
        obj.put("url", request.getRequestURL().toString());
        obj.put("message", ex.getMessage());
        obj.put("status", UNAUTHORIZED);


        return obj.toJSONString();
    }


//    @ExceptionHandler( Exception.class )
//    public ResponseEntity<?> handleAll(Exception ex) {
//
//        return new ResponseEntity<>(
//                "Error occurred",UNAUTHORIZED);
//    }
}
