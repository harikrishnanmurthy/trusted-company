package com.trustedcompany.fileupload.exception;

import java.util.logging.Logger;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@EnableWebMvc
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
 
	private final static Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class.getName());
	
    @ExceptionHandler({ Throwable.class, MultipartException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<String> handleGlobalException(Exception ex) {
    	LOGGER.severe("#########Invoking Global Error Handler#########");
    	LOGGER.severe(ex.getMessage());
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<String>(" Houston, we have a problem :O \n Please check logs for more details!!", headers, HttpStatus.NOT_FOUND);
    }
}
