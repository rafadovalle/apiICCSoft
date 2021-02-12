package com.rafael.inatel.exceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rafael.inatel.exception.VerificacaoException;

public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(VerificacaoException.class)
	public ResponseEntity<Object> handleNegocio(VerificacaoException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Errors error = new Errors();
		error.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
	}
}
