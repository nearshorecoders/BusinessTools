package com.org.pos.model;

import java.util.Date;

public class Cliente {

	private Integer idClienteAModificar;
	private String varNombre;
	private String varApellidoP;
	private String varApellidoM;
	private String varDireccion;
	private String varTelefono;
	private Date fechaDeRegistro;
	private Integer activo;
	
	public Integer getIdClienteAModificar() {
		return idClienteAModificar;
	}
	public void setIdClienteAModificar(Integer idClienteAModificar) {
		this.idClienteAModificar = idClienteAModificar;
	}
	public String getVarNombre() {
		return varNombre;
	}
	public void setVarNombre(String varNombre) {
		this.varNombre = varNombre;
	}
	public String getVarApellidoP() {
		return varApellidoP;
	}
	public void setVarApellidoP(String varApellidoP) {
		this.varApellidoP = varApellidoP;
	}
	public String getVarApellidoM() {
		return varApellidoM;
	}
	public void setVarApellidoM(String varApellidoM) {
		this.varApellidoM = varApellidoM;
	}
	public String getVarDireccion() {
		return varDireccion;
	}
	public void setVarDireccion(String varDireccion) {
		this.varDireccion = varDireccion;
	}
	public String getVarTelefono() {
		return varTelefono;
	}
	public void setVarTelefono(String varTelefono) {
		this.varTelefono = varTelefono;
	}
	public Date getFechaDeRegistro() {
		return fechaDeRegistro;
	}
	public void setFechaDeRegistro(Date fechaDeRegistro) {
		this.fechaDeRegistro = fechaDeRegistro;
	}
	public Integer getActivo() {
		return activo;
	}
	public void setActivo(Integer activo) {
		this.activo = activo;
	}
	
}
