package com.org.pos.model;

import java.util.Date;

public class Retiro {

	private Integer idretiro;
	private Double cantidad;
	private String descripcion;
	private Integer idUsuario;
	private Date fecha;
	private Integer consecutivo;
	private Integer sucursal_idsucursal;
	private String nombreDeQuienRetiro;
	private String nombreSucursal;
	
	public Integer getIdretiro() {
		return idretiro;
	}
	public void setIdretiro(Integer idretiro) {
		this.idretiro = idretiro;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}
	public Integer getSucursal_idsucursal() {
		return sucursal_idsucursal;
	}
	public void setSucursal_idsucursal(Integer sucursal_idsucursal) {
		this.sucursal_idsucursal = sucursal_idsucursal;
	}
	public String getNombreDeQuienRetiro() {
		return nombreDeQuienRetiro;
	}
	public void setNombreDeQuienRetiro(String nombreDeQuienRetiro) {
		this.nombreDeQuienRetiro = nombreDeQuienRetiro;
	}
	public String getNombreSucursal() {
		return nombreSucursal;
	}
	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}
	
}
