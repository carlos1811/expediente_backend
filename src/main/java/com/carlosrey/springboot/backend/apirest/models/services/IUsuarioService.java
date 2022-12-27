package com.carlosrey.springboot.backend.apirest.models.services;

import com.carlosrey.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioService {

	Usuario findByUsername(String username);
}
