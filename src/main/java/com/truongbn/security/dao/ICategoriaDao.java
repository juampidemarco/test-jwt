package com.truongbn.security.dao;

import com.truongbn.security.model.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface ICategoriaDao extends CrudRepository<Categoria, Long> {

}
