package com.projeto.dscatalog.services;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.dscatalog.dto.CategoryDTO;
import com.projeto.dscatalog.entities.Category;
import com.projeto.dscatalog.repositories.CategoryRepository;
import com.projeto.dscatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
    
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();
		// Função lambda
		List<CategoryDTO> listDto = list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		return listDto;
 
		/*Logica Tradicional
		 * List<CategoryDTO> listDto = new ArrayList<>(); for (Category cat : list) {
		 * listDto.add(new CategoryDTO(cat)); } return listDto;
		 */
	}
	@Transactional(readOnly = true)
	public CategoryDTO findById(long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Categoria não existe.")); 
		return new CategoryDTO(entity);
		
	}
	
	@Transactional(readOnly = true)
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getNome());
		entity = repository.save(entity);
		return new CategoryDTO(entity);
	}
	
	
	

}
