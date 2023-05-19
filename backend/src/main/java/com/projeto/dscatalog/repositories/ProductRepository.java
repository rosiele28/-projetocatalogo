package com.projeto.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.dscatalog.entities.Product;

public interface ProductRepository  extends JpaRepository<Product, Long>{

}
