package com.truongbn.security.service;


import com.truongbn.security.dao.response.CategoriaResponseRest;
import com.truongbn.security.model.Categoria;
import org.springframework.http.ResponseEntity;

public interface ICategoriaService {
	
	public ResponseEntity<CategoriaResponseRest> buscarCategorias();
	public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id);
	public ResponseEntity<CategoriaResponseRest> crearCategoria(Categoria categoria);
	public ResponseEntity<CategoriaResponseRest> actualizarCategoria(Categoria categoria, Long id);
	
}
