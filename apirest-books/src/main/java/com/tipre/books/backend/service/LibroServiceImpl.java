package com.tipre.books.backend.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tipre.books.backend.model.Libro;
import com.tipre.books.backend.model.dao.ILibroDao;
import com.tipre.books.backend.response.LibroResponseRest;

@Service
public class LibroServiceImpl implements ILibroService {
	
	private static final Logger log = LoggerFactory.getLogger(LibroServiceImpl.class);
	
	@Autowired
	private ILibroDao libroDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<LibroResponseRest> getLibros(){
		log.info("Inicio del metodo getLibros()");
		LibroResponseRest response = new LibroResponseRest();
		try {
			List<Libro> libros = (List<Libro>) libroDao.findAll();
			response.getLibroResponse().setLibro(libros);
			response.setMetaData("Respuesta ok", "200", "Respuesta Exitosa");
			
		} catch (Exception e) {
			response.setMetaData("Respuesta no ok", "-1", "Respuesta incorrecta");
			log.error("Error al consultar categoria", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<LibroResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<LibroResponseRest>(response,HttpStatus.OK);
	}
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<LibroResponseRest> getLibroPorId(Long id){
		log.info("Inicio del metodo getLibrosPorId()");
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> list = new ArrayList<Libro>(); 
		try {
			Optional<Libro> libros = libroDao.findById(id);
			if (libros.isPresent()) {
				list.add(libros.get());
				response.getLibroResponse().setLibro(list);
				response.setMetaData("Respuesta ok", "200", "Respuesta Exitosa");
			}else {
				log.error("No se encontro ningun libro por id");
				response.setMetaData("Respuesta no ok", "-1", "libro no encontrado");
				return new ResponseEntity<LibroResponseRest>(response,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("No se encontro ningun libro por id");
			response.setMetaData("Respuesta no ok", "-1", "libro no encontrado");
			e.getStackTrace();
			return new ResponseEntity<LibroResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<LibroResponseRest>(response,HttpStatus.OK);
	}
	
	@Override
	@Transactional
	public ResponseEntity<LibroResponseRest> saveLibro(Libro libro){
		log.info("Inicio del metodo saveLibro()");
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> list = new ArrayList<Libro>();
		try {
			Libro libros = libroDao.save(libro);
			if (libros != null) {
				list.add(libros);
				response.getLibroResponse().setLibro(list);
				response.setMetaData("Respuesta ok", "200", "Respuesta Exitosa");	
			}else {
				log.error("No se pudo guardar el libro");
				response.setMetaData("Respuesta no ok", "-1", "libro no guardado");
				return new ResponseEntity<LibroResponseRest>(response,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("No se pudo guardar el libro");
			response.setMetaData("Respuesta no ok", "-1", "libro no guardado");
			e.getStackTrace();
			return new ResponseEntity<LibroResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<LibroResponseRest>(response,HttpStatus.OK);
	}

}
