package com.tipre.books.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tipre.books.backend.model.Categoria;
import com.tipre.books.backend.response.CategoriaResponseRest;
import com.tipre.books.backend.service.ICategoriaService;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class CategoriaRestController {
	
	@Autowired
	private ICategoriaService service;
	
	@GetMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> consultarCategoria() {
		
		ResponseEntity<CategoriaResponseRest> response = service.buscarCategorias();
		return response;
		
	}
	
	@GetMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> buscarPorId(@PathVariable Long id){
		
		ResponseEntity<CategoriaResponseRest> response = service.buscarPorId(id);
		return response;
		
	}
	
	@PostMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> guardarCategoria(@RequestBody Categoria request){
		
		ResponseEntity<CategoriaResponseRest> response = service.crear(request);
		
		return response;
		
	}
	
	
	@PutMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> actualizarCategoria(@RequestBody Categoria categoria,@PathVariable Long id){
		ResponseEntity<CategoriaResponseRest> response = service.actualizar(categoria, id);
		return response;
	}
	
	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> eliminarCategoria(@PathVariable Long id){
		ResponseEntity<CategoriaResponseRest> response = service.eliminar(id);
		return response;
	}
}
