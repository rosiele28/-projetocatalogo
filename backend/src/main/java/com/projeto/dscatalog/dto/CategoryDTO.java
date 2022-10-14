package com.projeto.dscatalog.dto;

import java.io.Serializable;

import com.projeto.dscatalog.entities.Category;

public class CategoryDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String nome;
	
	public CategoryDTO() {
		
	}
	
	public CategoryDTO(long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	
	public CategoryDTO(Category entity) {
		this.id = entity.getId();
		this.nome = entity.getName();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	

}
