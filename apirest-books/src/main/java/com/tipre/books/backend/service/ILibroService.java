package com.tipre.books.backend.service;

import org.springframework.http.ResponseEntity;

import com.tipre.books.backend.model.Libro;
import com.tipre.books.backend.response.LibroResponseRest;

public interface ILibroService {
	
	public ResponseEntity<LibroResponseRest> getLibros();
	public ResponseEntity<LibroResponseRest> getLibroPorId(Long id);
	public ResponseEntity<LibroResponseRest> saveLibro(Libro libro);

}
