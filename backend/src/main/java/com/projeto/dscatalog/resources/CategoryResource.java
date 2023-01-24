package com.projeto.dscatalog.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.dscatalog.dto.CategoryDTO;
import com.projeto.dscatalog.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {    /*webservice para responder todas as categorias do banco de dados */
		List<CategoryDTO> list = service.findAll();

		return ResponseEntity.ok().body(list);

	}

	@GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable long id) {    /*webservice para responder todas as categorias por id */
	CategoryDTO dto = service.findById(id);
    return ResponseEntity.ok().body(dto);

	}
}
