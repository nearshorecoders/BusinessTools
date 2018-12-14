package com.org.pos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.pos.repository.SucursalRepository;

@Service
public class SucursalService {

	@Autowired
	SucursalRepository sucursalRepository;
	
	public Object getSucursalByLoguedUser(String idUser) {
		
		return sucursalRepository.getSucursalByLoguedUser(idUser);
	}
	
}
