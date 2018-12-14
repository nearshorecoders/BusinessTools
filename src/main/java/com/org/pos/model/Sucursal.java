package com.org.pos.model;

public class Sucursal {
	private Integer idsucursal;
	private String nombre;
	private String direccion;
	private String telefono;
	
	public Integer getIdsucursal() {
		return idsucursal;
	}
	public void setIdsucursal(Integer idsucursal) {
		this.idsucursal = idsucursal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
