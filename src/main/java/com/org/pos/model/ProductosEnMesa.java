package com.org.pos.model;

import java.util.List;

public class ProductosEnMesa {

	private Integer idMesa;
	private List<Productos> consumoMesa;
	
	public Integer getIdMesa() {
		return idMesa;
	}
	public void setIdMesa(Integer idMesa) {
		this.idMesa = idMesa;
	}
	public List<Productos> getConsumoMesa() {
		return consumoMesa;
	}
	public void setConsumoMesa(List<Productos> consumoMesa) {
		this.consumoMesa = consumoMesa;
	}
	
}
