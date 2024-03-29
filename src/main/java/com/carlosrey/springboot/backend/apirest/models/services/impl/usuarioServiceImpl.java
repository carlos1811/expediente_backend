package com.carlosrey.springboot.backend.apirest.models.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlosrey.springboot.backend.apirest.models.dao.IUsuarioDao;
import com.carlosrey.springboot.backend.apirest.models.entity.Usuario;
import com.carlosrey.springboot.backend.apirest.models.services.IUsuarioService;

@Service
public class usuarioServiceImpl implements IUsuarioService,UserDetailsService {


	@Autowired
	private IUsuarioDao usuarioDao;
	
	private final Logger logger = LoggerFactory.getLogger(usuarioServiceImpl.class);
	
	@Override
	@Transactional(readOnly=true)	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
				
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if (usuario == null) {
			logger.error("Error en el login: No existe el usuario" + username + "en el sistema");
			throw new UsernameNotFoundException("Error en el login: No existe el usuario" + username + "en el sistema");
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		return 	new User(usuario.getUsername(),usuario.getPassword() , usuario.getEnabled(),
				true, true,	true, authorities);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}


}
