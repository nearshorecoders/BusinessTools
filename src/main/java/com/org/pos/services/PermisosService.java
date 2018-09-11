package com.org.pos.services;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.pos.model.Usuario;
import com.org.pos.repository.RolHasPermisosRepository;
import com.org.pos.repository.UserRepository;

@Service
public class PermisosService {

	@Autowired
	RolHasPermisosRepository rolHasPermisosRepository; 

	@Autowired
	private UserRepository userRepository;
	
	public Map<String,Object> getAllPermisosByRol(Principal principal){
		Usuario u=userRepository.findByUser(principal.getName());
		
		return rolHasPermisosRepository.findPermisosByRol(u.getIdRol());
		
	}
}
