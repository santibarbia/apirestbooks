package com.tipre.books.backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.tipre.books.backend.model.Categoria;

public interface ICategoriaDao extends CrudRepository<Categoria, Long> {
	
	
}
