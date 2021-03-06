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

import com.tipre.books.backend.model.Categoria;
import com.tipre.books.backend.model.dao.ICategoriaDao;
import com.tipre.books.backend.response.CategoriaResponseRest;

@Service
public class CategoriaServiceImpl implements ICategoriaService{

	private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);
	
	@Autowired
	private ICategoriaDao categoriaDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
		
		log.info("Inicio del metodo buscarCategoria()");
		
		CategoriaResponseRest response = new CategoriaResponseRest();
		try {
			
			List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();
			
			response.getCategoriaResponse().setCategoria(categoria);
			
			response.setMetaData("Respuesta ok", "200", "Respuesta Exitosa");
			
		} catch (Exception e) {
			
			response.setMetaData("Respuesta no ok", "-1", "Respuesta incorrecta");
			log.error("Error al consultar categoria", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id){
		
		log.info("Inicio del metodo buscarPorId()");
		
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<Categoria>();
		try {
			
			Optional<Categoria> categoria = categoriaDao.findById(id);
			
			
			
			if(categoria.isPresent()) {
				list.add(categoria.get());
				response.getCategoriaResponse().setCategoria(list);
			}else {
				log.error("No se encontro ninguna categoria por id");
				response.setMetaData("Respuesta no ok", "-1", "Categoria no encontrada");
				return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.NOT_FOUND);
			}
			
			
			
		} catch (Exception e) {
			
			log.error("No se encontro ninguna categoria por id");
			response.setMetaData("Respuesta no ok", "-1", "Categoria no encontrada");
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.setMetaData("Respuesta ok", "200", "Respuesta Exitosa");
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	
	}
	
	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> crear(Categoria request){
			
			log.info("Inicio del metodo crear()");
			
			CategoriaResponseRest response = new CategoriaResponseRest();
			List<Categoria> list = new ArrayList<Categoria>();
			try {
				
				Categoria categoria = categoriaDao.save(request);
				
				if(categoria !=null) {
					list.add(categoria);
					response.getCategoriaResponse().setCategoria(list);
				}else {
					log.error("No se pudo grabar categoria");
					response.setMetaData("Respuesta no ok", "-1", "Categoria no guardada");
					return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.BAD_REQUEST);

				}
				
			} catch (Exception e) {
				
				log.error("Error en grabar categoria");
				response.setMetaData("Respuesta no ok", "-1", "Error al grabar");
				e.getStackTrace();
				return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
			response.setMetaData("Respuesta ok", "200", "Respuesta Exitosa");
			
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
		
		}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id) {
		
		log.info("Actualizar metodo");
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<Categoria>();
		
		try {
			
			Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);
			
			if (categoriaBuscada.isPresent()) {
				categoriaBuscada.get().setNombre(categoria.getNombre());
				categoriaBuscada.get().setDescripcion(categoria.getDescripcion());
				
				Categoria categoriaActualizada = categoriaDao.save(categoriaBuscada.get());
				
				if (categoriaActualizada != null) {
					response.setMetaData("Respuesta ok", "200", "Categoria Actualizada");
					list.add(categoriaActualizada);
					response.getCategoriaResponse().setCategoria(list);
				}else {
					log.error("Error al actualizar categoria");
					response.setMetaData("Respuesta no ok", "-1", "Error al actualizar");
					return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.BAD_REQUEST);
				}
				
			}else {
				log.error("Error al actualizar categoria");
				response.setMetaData("Respuesta no ok", "-1", "Error al actualizar");
				return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			log.error("Error al actualizar categoria",e.getMessage());
			response.setMetaData("Respuesta no ok", "-1", "Error al actualizar");
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetaData("Respuesta ok", "200", "Respuesta Exitosa");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CategoriaResponseRest> eliminar(Long id) {
		log.info("Eliminar metodo");
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		try {
			
			categoriaDao.deleteById(id);
			response.setMetaData("Respuesta ok", "200", "Se borro exitosamente");
			
			
			
		} catch (Exception e) {
			log.error("Error al borrar categoria",e.getMessage());
			response.setMetaData("Respuesta no ok", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}
	
	

}
