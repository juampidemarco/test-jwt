package com.truongbn.security.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.truongbn.security.dao.ICategoriaDao;
import com.truongbn.security.dao.response.CategoriaResponseRest;
import com.truongbn.security.model.Categoria;
import com.truongbn.security.service.ICategoriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class CategoriaServiceImpl implements ICategoriaService {

	private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

	@Autowired
	private ICategoriaDao categoriaDao;

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> buscarCategorias() {

		log.info("inicio metodo buscarCategorias()");

		CategoriaResponseRest response = new CategoriaResponseRest();

		try {

			List<Categoria> categorias = (List<Categoria>) categoriaDao.findAll();

			response.getCategoriaResponse().setCategoria(categorias);
			response.setMetadata("Respuesta ok", "00", "Respuestra exitosa");

		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Respuestra incorrecta");
			log.error("error al consultar categorias: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id) {

		log.info("inicio metodo buscarPorId()");

		CategoriaResponseRest response = new CategoriaResponseRest();
		
		List<Categoria> list = new ArrayList<>();

		try {

			Optional<Categoria> categoria = categoriaDao.findById(id);
			
			if (categoria.isPresent()) {
				list.add(categoria.get());
				response.getCategoriaResponse().setCategoria(list);
			}else {
				response.setMetadata("Respuesta nok", "-1", "Categoria no Encontrada");
				log.error("error al consultar categoria");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}


		} catch (Exception e) {
			
			log.error("error al consultar categoria: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetadata("Respuesta ok", "00", "Respuestra exitosa");

		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> crearCategoria(@RequestBody Categoria categoria) {

		log.info("inicio metodo crearCategoria()");

		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();
		

		try {

			Categoria categoriaGuardada = categoriaDao.save(categoria);
			
			
			if (categoriaGuardada != null) {
				list.add(categoriaGuardada);
				response.getCategoriaResponse().setCategoria(list);
			}else {
				response.setMetadata("Respuesta nok", "-1", "Error al guardar categoria");
				log.error("Error al guardar categori");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}


		} catch (Exception e) {
			
			log.error("error al guardar categoria: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetadata("Respuesta ok", "00", "Respuestra exitosa");

		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> actualizarCategoria(Categoria categoria, Long id) {
		
		log.info("inicio metodo crearCategoria()");
		
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();
		
		
		try {

			Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);
			
			if (categoriaBuscada.isPresent()) {
				categoriaBuscada.get().setNombre(categoria.getNombre());
				categoriaBuscada.get().setDescripcion(categoria.getDescripcion());
				
				Categoria categoriaActualizar = categoriaDao.save(categoriaBuscada.get());
				
				if (categoriaActualizar!=null) {
					response.setMetadata("Respuesta ok", "00", "Categoria Actualizada");
					list.add(categoriaActualizar);
					response.getCategoriaResponse().setCategoria(list);
				}else {
					log.error("error al actualizar categoria: ");
					return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
			}else {
				response.setMetadata("Respuesta nok", "-1", "Categoria no Encontrada");
				log.error("error al consultar categoria");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			 
			
		}catch (Exception e){
			log.error("error al actualizar categoria: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

}
