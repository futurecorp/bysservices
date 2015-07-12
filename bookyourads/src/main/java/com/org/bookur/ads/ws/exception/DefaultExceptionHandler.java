package com.org.bookur.ads.ws.exception;
import java.net.BindException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.org.bookur.ads.ws.constants.ErrorConstants;
import com.org.bookur.ads.ws.models.response.AdResponse;

@ControllerAdvice
public class DefaultExceptionHandler {
 
	/*http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html#mvc-ann-controller-advice */	
	
    @ExceptionHandler({MissingServletRequestParameterException.class,
            UnsatisfiedServletRequestParameterException.class,
            HttpRequestMethodNotSupportedException.class,
            ServletRequestBindingException.class,BindException.class,TypeMismatchException.class
    })
    public ResponseEntity<String> inValidRequest(HttpServletRequest request,
			HttpServletResponse response,Exception e) 
	        {
		    return new ResponseEntity<String>(new AdResponse(ErrorConstants.INVALID_REQUEST,"MissingServletRequest", request.getRequestURL().toString(), false).getResponse(), HttpStatus.BAD_REQUEST);
	}

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class,HttpMediaTypeNotAcceptableException.class})
    public ResponseEntity<String> inValidMediaType(HttpServletRequest request,
			HttpServletResponse response,Exception e) 
	        {
		    return new ResponseEntity<String>(new AdResponse(ErrorConstants.INVALID_REQUEST,"HttpMediaTypeNotSupported", request.getRequestURL().toString(), false).getResponse(), HttpStatus.NOT_ACCEPTABLE);
	}
    @ExceptionHandler({ConversionNotSupportedException.class,HttpMessageNotWritableException.class})
    public ResponseEntity<AdResponse> dataConversionError(HttpServletRequest request,
			HttpServletResponse response,Exception e) 
	        {
    	AdResponse errorRes=new AdResponse(ErrorConstants.INTERNAL_ISSUE, "ConversionNotSupported", request.getRequestURL().toString(), false);
		    return new ResponseEntity<AdResponse>(errorRes, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    @ExceptionHandler({AdException.class})
    public ResponseEntity<String> handleAllException(HttpServletRequest request,
			HttpServletResponse response,AdException e) 
	        {
    		AdResponse errorRes=new AdResponse(e.getErrorCode(), "bad request", request.getRequestURL().toString(), false);
    		if(e.getErrorCode().equalsIgnoreCase("000"))
		    return new ResponseEntity<String>(errorRes.getResponse(), HttpStatus.NO_CONTENT);
    		return new ResponseEntity<String>(errorRes.getResponse(), HttpStatus.BAD_REQUEST);
	}
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleAllException(HttpServletRequest request,
			HttpServletResponse response,Exception e) 
	        {
    	AdResponse errorRes=new AdResponse(ErrorConstants.CONNECTION_ISSUE, "bad request", request.getRequestURL().toString(), false);
		    return new ResponseEntity<String>(errorRes.getResponse(), HttpStatus.BAD_REQUEST);
	}
    
    
  
}