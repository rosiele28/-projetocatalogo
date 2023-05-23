package com.projeto.dscatalog.services;


import java.util.Optional; 
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.dscatalog.dto.CategoryDTO;
import com.projeto.dscatalog.entities.Category;
import com.projeto.dscatalog.repositories.CategoryRepository;
import com.projeto.dscatalog.services.exceptions.DatabaseException;
import com.projeto.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
    
	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {
		Page<Category> list = repository.findAll(pageRequest);
		return list.map(x -> new CategoryDTO(x));
 
	
	}
	@Transactional(readOnly = true)
	public CategoryDTO findById(long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Categoria não existe.")); 
		return new CategoryDTO(entity);
		
	}
	
	@Transactional(readOnly = true)
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getNome());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}

	@Transactional(readOnly = true)
	public CategoryDTO update(Long id, CategoryDTO dto) {
		
		try {
		Category entity = repository.getReferenceById(id);
		entity.setName(dto.getNome());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Categoria não existe." + id);
		}
	}
	
	
	
	public void delete(long id) {
		try {
		repository.deleteById(id);
		} catch (EmptyResultDataAccessException e ){
			throw new ResourceNotFoundException("id not found " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException(" Integrity violation");
		}
		
	}

}
