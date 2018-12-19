package com.org.pos.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.org.pos.model.EstadoDeCuenta;
import com.org.pos.model.ReporteVentas;

@Repository
public class EstadoDeCuentaRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(EstadoDeCuentaRepository.class);
	
    @Autowired
    ProductosRepository productosRepository;
    
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    @Qualifier("exchangeDS")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	public Map<String,Object> getEstadoDeCuentaByClient(Integer clientID) {
		 
	    	Map<String,Object> estadoDeCuenta=new HashMap<String,Object>();
	    	List<EstadoDeCuenta> listaEstadoCuenta=new ArrayList<EstadoDeCuenta>();

	        try{
	            String SQLString="SELECT a.consecutivo,a.cuentaTotal,a.ingresoACuenta,a.restante,a.nuevoRestante,a.fecha, "+
	            			     " b.idProductos as 'idProducto',b.descripcion,b.marca,b.estatus,c.nombre,c.apellidop,c.apellidom,d.idstatusEstadoCuenta,d.descripcion as 'descripcionEstatus' "+
	            				 " FROM estadodecuenta as a "+ 
	                             " INNER JOIN productos as b on b.idproductos=a.productos_idproductos "+
	                             " INNER JOIN cliente as c on c.idcliente=a.cliente_idcliente "+
	                             " INNER JOIN statusestadocuenta as d on d.idstatusestadocuenta=a.statusestadocuenta_idstatusestadocuenta "+
	                             " where a.cliente_idcliente="+clientID+" order by a.fecha,a.consecutivo";       
	           
	            System.out.println(SQLString);
	            
	        	List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQLString);
	        	Double sumaTotalPeriodo=0.0;
	        	for (Map row : rows) {
	        		EstadoDeCuenta edoCuentaRow = new EstadoDeCuenta();
	        		edoCuentaRow.setConsecutivo((Integer)(row.get("consecutivo")));
	        		edoCuentaRow.setCuentaTotal((Double)(row.get("cuentaTotal")));
	        		edoCuentaRow.setFecha((Date)(row.get("fecha")));
	        		edoCuentaRow.setIdProducto((Integer)(row.get("idProducto")));
	        		edoCuentaRow.setIdStatus((Integer)(row.get("idstatusEstadoCuenta")));
	        		edoCuentaRow.setIngresoACuenta((Double)(row.get("ingresoACuenta")));
	        		edoCuentaRow.setNuevoRestante((Double)(row.get("nuevoRestante")));
	        		edoCuentaRow.setRestante((Double)(row.get("restante")));
	        		edoCuentaRow.setDescripcionProducto((String)(row.get("descripcion")));
	        		edoCuentaRow.setMarcaProducto((String)(row.get("marca")));
	        		edoCuentaRow.setEstatusProducto((Integer)(row.get("estatus")));
	        		edoCuentaRow.setEstatusProductoDescripcion((String)(row.get("descripcionEstatus")));
	        		
	        		listaEstadoCuenta.add(edoCuentaRow);
	        		
	        		Double cantidad=(Double)(row.get("cantidad"));
	        		Double precioTotal=(Double)(row.get("precioTotal"));
	        		 if(cantidad>1){
	                     sumaTotalPeriodo+=precioTotal*cantidad;
	                 }else{
	                     sumaTotalPeriodo+=precioTotal;
	                 }
	        		
	        	}
	        	estadoDeCuenta.put("totalEfectivoEnCuentas",sumaTotalPeriodo);
	           

	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        
	        estadoDeCuenta.put("listaEstadoDeCuenta",listaEstadoCuenta);
	        
	       return estadoDeCuenta; 
	 }
	 
	 public Map<String,Object> getEstadoDeCuentaByClientAndStatus(Integer clientID,Integer status) {
	    	Map<String,Object> estadoDeCuenta=new HashMap<String,Object>();
	    	List<EstadoDeCuenta> listaEstadoCuenta=new ArrayList<EstadoDeCuenta>();

	        try{
	            String SQLString="SELECT a.consecutivo,a.cuentaTotal,a.ingresoACuenta,a.restante,a.nuevoRestante,a.fecha, "+
	            			     " b.idProductos as 'idProducto',b.descripcion,b.marca,b.estatus,c.nombre,c.apellidop,c.apellidom,d.idstatusEstadoCuenta,d.descripcion as 'descripcionEstatus' "+
	            				 " FROM estadodecuenta as a "+ 
	                             " INNER JOIN productos as b on b.idproductos=a.productos_idproductos "+
	                             " INNER JOIN cliente as c on c.idcliente=a.cliente_idcliente "+
	                             " INNER JOIN statusestadocuenta as d on d.idstatusestadocuenta=a.statusestadocuenta_idstatusestadocuenta "+
	                             " where a.cliente_idcliente="+clientID+" AND d.idstatusestadocuenta="+status+" a.order by a.fecha,a.consecutivo";       
	           
	            System.out.println(SQLString);
	            
	        	List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQLString);
	        	Double sumaTotalPeriodo=0.0;
	        	for (Map row : rows) {
	        		EstadoDeCuenta edoCuentaRow = new EstadoDeCuenta();
	        		edoCuentaRow.setConsecutivo((Integer)(row.get("consecutivo")));
	        		edoCuentaRow.setCuentaTotal((Double)(row.get("cuentaTotal")));
	        		edoCuentaRow.setFecha((Date)(row.get("fecha")));
	        		edoCuentaRow.setIdProducto((Integer)(row.get("idProducto")));
	        		edoCuentaRow.setIdStatus((Integer)(row.get("idstatusEstadoCuenta")));
	        		edoCuentaRow.setIngresoACuenta((Double)(row.get("ingresoACuenta")));
	        		edoCuentaRow.setNuevoRestante((Double)(row.get("nuevoRestante")));
	        		edoCuentaRow.setRestante((Double)(row.get("restante")));
	        		edoCuentaRow.setDescripcionProducto((String)(row.get("descripcion")));
	        		edoCuentaRow.setMarcaProducto((String)(row.get("marca")));
	        		edoCuentaRow.setEstatusProducto((Integer)(row.get("estatus")));
	        		edoCuentaRow.setEstatusProductoDescripcion((String)(row.get("descripcionEstatus")));
	        		
	        		listaEstadoCuenta.add(edoCuentaRow);
	        		
	        		Double cantidad=(Double)(row.get("cantidad"));
	        		Double precioTotal=(Double)(row.get("precioTotal"));
	        		 if(cantidad>1){
	                     sumaTotalPeriodo+=precioTotal*cantidad;
	                 }else{
	                     sumaTotalPeriodo+=precioTotal;
	                 }
	        		
	        	}
	        	estadoDeCuenta.put("totalEfectivoEnCuentas",sumaTotalPeriodo);
	           

	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        
	        estadoDeCuenta.put("listaEstadoDeCuenta",listaEstadoCuenta);
	        
	       return estadoDeCuenta; 
	 }
	
	 public EstadoDeCuenta registrarAbono(EstadoDeCuenta abono) {
         Integer consecutivoVenta=1;
		 try {
          	//agregar a la consulta el usuario y tambien la sucursal
             String sqlString="select max(consecutivoVenta) from estadodecuenta where cliente_idcliente="+abono.getIdCliente()+" AND productos_idproductos="+abono.getIdProducto();
          	Integer consecMax=jdbcTemplate.queryForObject(sqlString, Integer.class);
          	if(consecMax!=null) {
          		consecutivoVenta=consecMax+1;
          	}
          }catch (Exception e){
          	LOGGER.error("Error", e);
              throw e;
          }
		 
         String sqlString="INSERT INTO `estadodecuenta` (`cuentaTotal`,`ingresoACuenta`,`restante`,`nuevoRestante`,`cliente_idcliente`,`productos_idproductos`,`statusestadodecuenta_idstatusestadodecuenta`,`consecutivo`) "
                 + " VALUES ("+abono.getCuentaTotal()+","+abono.getIngresoACuenta()+", "+abono.getRestante()+","+abono.getNuevoRestante()+","+abono.getIdCliente()+","+abono.getIdProducto()+","+abono.getIdStatus()+","+consecutivoVenta+" )";

         jdbcTemplate.update(sqlString);
		
		 return abono;
	 }
	 
	 public List<EstadoDeCuenta> getHistoricDetailByProduct(Integer idCliente,Integer idProducto) {
	    	List<EstadoDeCuenta> listaEstadoCuenta=new ArrayList<EstadoDeCuenta>();

	        try{
	            String SQLString="SELECT a.consecutivo,a.cuentaTotal,a.ingresoACuenta,a.restante,a.nuevoRestante,a.fecha, "+
	            			     " b.idProductos as 'idProducto',b.descripcion,b.marca,b.estatus,c.nombre,c.apellidop,c.apellidom,d.idstatusEstadoCuenta,d.descripcion as 'descripcionEstatus' "+
	            				 " FROM estadodecuenta as a "+ 
	                             " INNER JOIN productos as b on b.idproductos=a.productos_idproductos "+
	                             " INNER JOIN cliente as c on c.idcliente=a.cliente_idcliente "+
	                             " INNER JOIN statusestadocuenta as d on d.idstatusestadocuenta=a.statusestadocuenta_idstatusestadocuenta "+
	                             " where a.cliente_idcliente="+idCliente+" AND b.idProductos="+idProducto+" a.order by a.fecha,a.consecutivo";       
	           
	            System.out.println(SQLString);
	            
	        	List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQLString);
	        	Double sumaTotalPeriodo=0.0;
	        	for (Map row : rows) {
	        		EstadoDeCuenta edoCuentaRow = new EstadoDeCuenta();
	        		edoCuentaRow.setConsecutivo((Integer)(row.get("consecutivo")));
	        		edoCuentaRow.setCuentaTotal((Double)(row.get("cuentaTotal")));
	        		edoCuentaRow.setFecha((Date)(row.get("fecha")));
	        		edoCuentaRow.setIdProducto((Integer)(row.get("idProducto")));
	        		edoCuentaRow.setIdStatus((Integer)(row.get("idstatusEstadoCuenta")));
	        		edoCuentaRow.setIngresoACuenta((Double)(row.get("ingresoACuenta")));
	        		edoCuentaRow.setNuevoRestante((Double)(row.get("nuevoRestante")));
	        		edoCuentaRow.setRestante((Double)(row.get("restante")));
	        		edoCuentaRow.setDescripcionProducto((String)(row.get("descripcion")));
	        		edoCuentaRow.setMarcaProducto((String)(row.get("marca")));
	        		edoCuentaRow.setEstatusProducto((Integer)(row.get("estatus")));
	        		edoCuentaRow.setEstatusProductoDescripcion((String)(row.get("descripcionEstatus")));
	        		
	        		listaEstadoCuenta.add(edoCuentaRow);
	        	}
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        
	        return listaEstadoCuenta;
	 }
	 
}
