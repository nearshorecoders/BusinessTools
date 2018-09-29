package com.org.pos.model;

public class DetalleVenta {
	private Integer idDetalleVenta;
	private Double cantidad;
	private Double precioTotal;
	private String descripcionProd;
	private String tamanio;
	private String unidadMedida;
	private Integer productos_idproductos;
	private Integer extras_idextras;
	private Integer venta_idVenta;
	private Integer consecutivoVenta;
	
	public Integer getIdDetalleVenta() {
		return idDetalleVenta;
	}
	public void setIdDetalleVenta(Integer idDetalleVenta) {
		this.idDetalleVenta = idDetalleVenta;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public Double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(Double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public String getDescripcionProd() {
		return descripcionProd;
	}
	public void setDescripcionProd(String descripcionProd) {
		this.descripcionProd = descripcionProd;
	}
	public String getTamanio() {
		return tamanio;
	}
	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public Integer getProductos_idproductos() {
		return productos_idproductos;
	}
	public void setProductos_idproductos(Integer productos_idproductos) {
		this.productos_idproductos = productos_idproductos;
	}
	public Integer getExtras_idextras() {
		return extras_idextras;
	}
	public void setExtras_idextras(Integer extras_idextras) {
		this.extras_idextras = extras_idextras;
	}
	public Integer getVenta_idVenta() {
		return venta_idVenta;
	}
	public void setVenta_idVenta(Integer venta_idVenta) {
		this.venta_idVenta = venta_idVenta;
	}
	public Integer getConsecutivoVenta() {
		return consecutivoVenta;
	}
	public void setConsecutivoVenta(Integer consecutivoVenta) {
		this.consecutivoVenta = consecutivoVenta;
	}
	
}
