package com.tipre.books.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tipre.books.backend.model.Libro;
import com.tipre.books.backend.response.LibroResponseRest;
import com.tipre.books.backend.service.ILibroService;

@RestController
@RequestMapping("/v2")
public class LibroRestController {
	
	@Autowired
	private ILibroService service;
	
	@GetMapping("/libros")
	public ResponseEntity<LibroResponseRest> buscarLibros(){
		
		ResponseEntity<LibroResponseRest> response = service.getLibros();
		return response;
	}
	
	@GetMapping("/libros/{id}")
	public ResponseEntity<LibroResponseRest> buscarLibro(@PathVariable Long id){
		ResponseEntity<LibroResponseRest> response = service.getLibroPorId(id);
		return response;
	}
	
	@PostMapping("/libros")
	public ResponseEntity<LibroResponseRest> guardarLibro(@RequestBody Libro libro){
		ResponseEntity<LibroResponseRest> response = service.saveLibro(libro);
		return response;
	}
	
}
