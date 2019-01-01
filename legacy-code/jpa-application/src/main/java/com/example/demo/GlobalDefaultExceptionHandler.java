package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel on 2017. 7. 9..
 * Global ExceptionHandler
 *
 * @see <a href="https://stackoverflow.com/questions/23582534/how-to-handle-exceptions-in-spring-mvc-differently-for-html-and-json-requests">How to handle exceptions in Spring MVC differently for HTML and JSON requests</a>
 * @see <a href="https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc">Exception Handling in Spring MVC</a>
 */

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    public static final Logger log = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    /*
    VER 1)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ErrorInfo handleConflict(HttpServletRequest req, Exception e) {
        return new ErrorInfo(HttpStatus.NOT_FOUND.value(), e.getClass().getSimpleName(), e.getMessage());
    }
    */


    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<Map<String, ErrorInfo>> handleNotFound(HttpServletRequest req, Exception e) {
        Map<String, ErrorInfo> errorResponseBody = new HashMap<>();
        errorResponseBody.put("error", new ErrorInfo(HttpStatus.NOT_FOUND.value(), e.getClass().getSimpleName(), e.getMessage()));
        return new ResponseEntity<>(errorResponseBody, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResponseEntity<Map<String, ErrorInfo>> handleValidation(HttpServletRequest req, Exception e) {
        Map<String, ErrorInfo> errorResponseBody = new HashMap<>();
        errorResponseBody.put("error", new ErrorInfo(HttpStatus.BAD_REQUEST.value(), e.getClass().getSimpleName(), e.getMessage()));
        return new ResponseEntity<>(errorResponseBody, HttpStatus.BAD_REQUEST);
    }


}
