package com.tipre.books.backend.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tipre.books.backend.model.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
	
	public Usuario findByUsuario(String usuario);
	
	@Query("select u from Usuario u where u.usuario = ?1 ")
	public Usuario findByIdUsuarioV2(String usuario);
	
}
