package com.org.pos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.pos.model.Empresa;
import com.org.pos.repository.ConfiguracionRepository;

@Service
public class EmpresaService {

	@Autowired
	ConfiguracionRepository empresaRepository;
	
	public Empresa crearEmpresa() {
		
		return null;
	}
	
	public Empresa actualizarEmpresa() {
		
		return null;
	}	
	
}
