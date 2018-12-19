package com.org.pos.model;

import java.util.Date;

public class Caja {

	private Integer idCaja;
	private Double inicioDelDia;
	private Double finalDelDia;
	private Date fecha;
	private Integer sucursal_idsucursal;
	
	public Integer getIdCaja() {
		return idCaja;
	}
	public void setIdCaja(Integer idCaja) {
		this.idCaja = idCaja;
	}
	public Double getInicioDelDia() {
		return inicioDelDia;
	}
	public void setInicioDelDia(Double inicioDelDia) {
		this.inicioDelDia = inicioDelDia;
	}
	public Double getFinalDelDia() {
		return finalDelDia;
	}
	public void setFinalDelDia(Double finalDelDia) {
		this.finalDelDia = finalDelDia;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getSucursal_idsucursal() {
		return sucursal_idsucursal;
	}
	public void setSucursal_idsucursal(Integer sucursal_idsucursal) {
		this.sucursal_idsucursal = sucursal_idsucursal;
	}
	
}
