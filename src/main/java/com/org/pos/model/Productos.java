package com.org.pos.model;

public class Productos {
	   
    private Integer id;
    private String codigo;
    private String Descripcion;
    private Double precioCompra;
    private Double precioVenta;            
    private String presentacion;
    private String unidadMedida;
    private Integer estatus;
    private Double unidadesEnCaja;
    private Double cantidadMinima;
    private Double cantidadAceptable;
    private Integer idSucursal;
    
    
    ///geters y setters 
    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    /**
     * @return the precioCompra
     */
    public Double getPrecioCompra() {
        return precioCompra;
    }

    /**
     * @param precioCompra the precioCompra to set
     */
    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    /**
     * @return the precioVenta
     */
    public Double getPrecioVenta() {
        return precioVenta;
    }

    /**
     * @param precioVenta the precioVenta to set
     */
    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    /**
     * @return the presentacion
     */
    public String getPresentacion() {
        return presentacion;
    }

    /**
     * @param presentacion the presentacion to set
     */
    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    /**
     * @return the unidadMedida
     */
    public String getUnidadMedida() {
        return unidadMedida;
    }

    /**
     * @param unidadMedida the unidadMedida to set
     */
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    /**
     * @return the estatus
     */
    public Integer getEstatus() {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     */
    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    /**
     * @return the unidadesEnCaja
     */
    public Double getUnidadesEnCaja() {
        return unidadesEnCaja;
    }

    /**
     * @param unidadesEnCaja the unidadesEnCaja to set
     */
    public void setUnidadesEnCaja(Double unidadesEnCaja) {
        this.unidadesEnCaja = unidadesEnCaja;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

	public Double getCantidadMinima() {
		return cantidadMinima;
	}

	public void setCantidadMinima(Double cantidadMinima) {
		this.cantidadMinima = cantidadMinima;
	}

	public Double getCantidadAceptable() {
		return cantidadAceptable;
	}

	public void setCantidadAceptable(Double cantidadAceptable) {
		this.cantidadAceptable = cantidadAceptable;
	}

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal= idSucursal;
	}
    
    
}

