package com.projeto.dscatalog.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.projeto.dscatalog.services.exceptions.EntityNotFoundException;

@ControllerAdvice
public class ResourceExecptionHandler { /*controlador de tratamento de exceção*/
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandError> entityNotFound (EntityNotFoundException e, HttpServletRequest request){
		StandError erro = new StandError();
		erro.setTimestamp(Instant.now());
		erro.setStatus(HttpStatus.NOT_FOUND.value());
		erro.setError("Erro !");
		erro.setMensagem(e.getMessage());
		erro.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
		
	}

}