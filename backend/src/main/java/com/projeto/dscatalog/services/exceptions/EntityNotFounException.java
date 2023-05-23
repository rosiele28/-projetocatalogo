package com.projeto.dscatalog.services.exceptions;

public class EntityNotFounException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	
	public EntityNotFounException(String msg) {
		super(msg);
	}
 
}
