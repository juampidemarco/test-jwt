package com.truongbn.security.controller;

import com.truongbn.security.dao.response.CategoriaResponseRest;
import com.truongbn.security.model.Categoria;
import com.truongbn.security.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CategoriaRestController {

	@Autowired
	private ICategoriaService service;

	@GetMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> consultarCategorias() {

		ResponseEntity<CategoriaResponseRest> response = service.buscarCategorias();
		return response;
	}

	@GetMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> consultaPorId(@PathVariable Long id) {
		ResponseEntity<CategoriaResponseRest> response = service.buscarPorId(id);
		return response;
	}
	
	@PostMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> crearCategoria(@RequestBody Categoria categoria) {
		ResponseEntity<CategoriaResponseRest> response = service.crearCategoria(categoria);
		return response;
	}
	
	@PutMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> actualizarCategoria(@RequestBody Categoria categoria, @PathVariable Long id) {
		ResponseEntity<CategoriaResponseRest> response = service.actualizarCategoria(categoria, id);
		return response;
	}


}
