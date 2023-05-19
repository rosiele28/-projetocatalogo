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

import com.projeto.dscatalog.dto.ProductDTO;
import com.projeto.dscatalog.entities.Product;
import com.projeto.dscatalog.repositories.ProductRepository;
import com.projeto.dscatalog.services.exceptions.DatabaseException;
import com.projeto.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
    
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		Page<Product> list = repository.findAll(pageRequest);
		return list.map(x -> new ProductDTO(x));
 
	
	}
	@Transactional(readOnly = true)
	public ProductDTO findById(long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Categoria não existe.")); 
		return new ProductDTO(entity, entity.getCategories());
		
	}
	
	@Transactional(readOnly = true)
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		//entity.setName(dto.getName());
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}

	@Transactional(readOnly = true)
	public ProductDTO update(long id, ProductDTO dto) {
		
		try {
		Product entity = repository.getReferenceById(id);
		//entity.setName(dto.getName());
		entity = repository.save(entity);
		return new ProductDTO(entity);
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
