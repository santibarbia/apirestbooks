package com.tipre.books.backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.tipre.books.backend.model.Libro;

public interface ILibroDao extends CrudRepository<Libro, Long> {

}
