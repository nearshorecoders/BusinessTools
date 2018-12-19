package com.org.pos.model;

import java.util.Date;

public class Inconsistencia {

	private Integer idInconsistencia;
	private Date fecha;
	private String detalle;
	private Integer idUsuario;
	private Integer idProducto;
	
	public Integer getIdInconsistencia() {
		return idInconsistencia;
	}
	public Date getFecha() {
		return fecha;
	}
	public String getDetalle() {
		return detalle;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdInconsistencia(Integer idInconsistencia) {
		this.idInconsistencia = idInconsistencia;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
}
