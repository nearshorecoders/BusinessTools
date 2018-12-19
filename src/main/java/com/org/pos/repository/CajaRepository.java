package com.org.pos.repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;
import com.org.pos.model.Caja;
import com.org.pos.model.ReporteVentas;
import com.org.pos.model.Retiro;

@Repository
public class CajaRepository {

	private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("exchangeDS")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	public Map<String,Object> obtenerGastosDelDia(String fi,String ff,Integer idSucursal){
        Map<String,Object> resultado=new HashMap<String,Object>();
		List<Retiro> listadoRetiros=new ArrayList<Retiro>();
        
        String sqlString="select a.consecutivo,a.cantidad,a.descripcion as motivo,b.nombre, b.apellidop, b.apellidom from " +
                         " retiro a inner join usuarios b on a.usuario=b.idUsuario   where (a.fecha BETWEEN '"+fi+"' AND '"+ff+"') and a.sucursal_idsucursal="+idSucursal;
        System.out.println(sqlString);
    
    try{
       
    	List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlString);
    	Double sumaTotalGastos=0.0;

    	for (Map row : rows) {
    		Retiro retiroRow = new Retiro();
    		retiroRow.setConsecutivo((Integer)(row.get("consecutivo")));
    		retiroRow.setCantidad((Double)(row.get("cantidad")));
    		retiroRow.setDescripcion((String)(row.get("motivo")));
    		String nombre=(String)row.get("nombre");
    		String apellidoP=(String)row.get("apellidop");
    		String apellidoM=(String)row.get("apellidom");
    		retiroRow.setNombreDeQuienRetiro(nombre+" "+apellidoP+" "+apellidoM);	
    		Double cantidad=(Double)(row.get("cantidad"));
    		sumaTotalGastos+=cantidad;
    		listadoRetiros.add(retiroRow);
    	}
    	
      Double pretotal=calcularTotalVentasCaja(fi,ff,0.0,idSucursal);
      Double operacionBalance=pretotal-sumaTotalGastos;
      
      resultado.put("listadoRetiros", listadoRetiros);
      resultado.put("totalGastos", sumaTotalGastos);
      resultado.put("totalVentas",pretotal);
      resultado.put("totalBalance",operacionBalance);
      resultado.put("fechaInicial",fi);
      resultado.put("fechaInicial",ff);
      
    }catch(Exception e){
        e.printStackTrace();
    }
    
    return resultado;
    
  }
	
	public Map<String,Object> obtenerSoloGastosDelDia(String fi,String ff,Integer idSucursal){
        Map<String,Object> resultado=new HashMap<String,Object>();
		List<Retiro> listadoRetiros=new ArrayList<Retiro>();
        
        String sqlString="select a.fecha,a.consecutivo,a.cantidad,a.descripcion as motivo,b.nombre, b.apellidop, b.apellidom, c.nombre as 'nombreSucursal' from " +
                         " retiro a INNER JOIN usuarios b ON a.usuario=b.idUsuario INNER JOIN sucursal c ON a.sucursal_idsucursal=c.idsucursal  where (a.fecha BETWEEN '"+fi+"' AND '"+ff+"') and a.sucursal_idsucursal="+idSucursal+" ORDER BY a.fecha";
    
    try{
       
    	List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlString);

    	for (Map row : rows) {
    		Retiro retiroRow = new Retiro();
    		retiroRow.setConsecutivo((Integer)(row.get("consecutivo")));
    		retiroRow.setCantidad((Double)(row.get("cantidad")));
    		retiroRow.setDescripcion((String)(row.get("motivo")));
    		String nombre=(String)row.get("nombre");
    		String apellidoP=(String)row.get("apellidop");
    		String apellidoM=(String)row.get("apellidom");
    		retiroRow.setNombreDeQuienRetiro(nombre+" "+apellidoP+" "+apellidoM);	
    		retiroRow.setFecha((Date)(row.get("fecha")));
    		retiroRow.setNombreSucursal((String)row.get("nombreSucursal"));
    		listadoRetiros.add(retiroRow);
    	}
      
      resultado.put("listadoRetiros", listadoRetiros);
      
    }catch(Exception e){
        e.printStackTrace();
    }
    
    return resultado;
    
  }	
	
	public Map<String,Object> obtenerTodosLosCortesDeCaja(String fi,String ff,Integer idSucursal){
        Map<String,Object> resultado=new HashMap<String,Object>();
		List<Caja> listadoCaja=new ArrayList<Caja>();
        
        String sqlString="select * from Caja where (fecha BETWEEN '"+fi+"' AND '"+ff+"') and sucursal_idsucursal="+idSucursal+" ORDER BY fecha";
    
    try{
       
    	List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlString);

    	for (Map row : rows) {
    		Caja retiroRow = new Caja();
    		retiroRow.setIdCaja((Integer)(row.get("idCaja")));
    		retiroRow.setInicioDelDia((Double)(row.get("inicioDelDia")));
    		retiroRow.setFinalDelDia((Double)(row.get("finalDelDia")));
    		retiroRow.setFecha((Date)(row.get("fecha")));	
    		retiroRow.setSucursal_idsucursal((Integer)(row.get("sucursal_idsucursal")));
    		listadoCaja.add(retiroRow);
    	}
      
      resultado.put("listadoCortesDeCaja", listadoCaja);
      
    }catch(Exception e){
        e.printStackTrace();
    }
    
    return resultado;
    
  }	
	
public Double calcularTotalVentasCaja(String fi,String ff,Double ivaConfigurado,Integer idSucursal){
			Double sumaTotalPeriodo=0.0;
			Double sumaIvaPeriodo=0.0;
	        try{
	            
	            String SQLString="SELECT a.idVenta as 'No. de venta',a.total,a.fechaVenta,b.descripcionProd,b.precioTotal,b.cantidad as 'Cantidad vendida', "+
	                             " b.precioTotal*b.cantidad as 'Subtotal',c.NombreCompleto as 'Vendido por', "+
	                             " c.NombreUsuario FROM venta as a inner join detalleventa b on a.idVenta=b.Venta_idVenta "+
	                             " inner join usuarios as c on c.idUsuario=a.usuarios_idUsuario "+
	                             " where a.fechaVenta BETWEEN '"+fi+"' AND '"+ff+"' and c.sucursal_idsucursal="+idSucursal;
	            
	            String usuarioSeleccionado="---";
	            if(usuarioSeleccionado.equals("---")){        
	                 SQLString+=" order by a.idventa,b.consecutivoVenta";
	            }else{
	                
	                 SQLString+=" and c.NombreCompleto='"+usuarioSeleccionado+"' ";
	                
	                 SQLString+=" order by a.idventa,b.consecutivoVenta";
	                 
	            }        

	            String nombreUsuario="---";
	           
	            List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQLString);
	            
	        	for (Map row : rows) {
	        		
	        		Double cantidad=(Double)(row.get("cantidad"));
	        		Double precioTotal=(Double)(row.get("precioTotal"));
	        		 if(cantidad>1){
	                     sumaTotalPeriodo+=precioTotal*cantidad;
	                     sumaIvaPeriodo+=(precioTotal*cantidad)*ivaConfigurado;
	                 }else{
	                     sumaTotalPeriodo+=precioTotal;
	                     sumaIvaPeriodo+=precioTotal*ivaConfigurado;
	                 }
	        		
	        	}
	            
	        }catch(Exception e){
	        
	        }
	        return sumaTotalPeriodo;
	    }

	 public Integer realizarCorteDeCaja(Double balance,Integer idSucursal) {                                         
	        Integer resultUpdate=0;
	        /////obtenemos el maximo ingresado en la caja
	        //String sqlString="Select max(idCaja) as 'idCaja',inicioDelDia,finalDelDia,fecha from caja WHERE sucursal_idsucursal= "+idSucursal;
	        String sqlString="Select max(idCaja) as 'idCaja' WHERE sucursal_idsucursal= "+idSucursal;
	        try{
	         
	         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	         	int idCaja=0;
	         	Integer consecMax=jdbcTemplate.queryForObject(sqlString, Integer.class);
	            if(consecMax!=null){
	                idCaja=consecMax;
	            }
	            
	            if(idCaja!=0){        
	                String sqlString2="UPDATE caja SET finalDelDia="+balance+ " WHERE idCaja="+idCaja+" AND sucursal_idsucursal="+idSucursal;
	                resultUpdate=jdbcTemplate.update(sqlString2);
	            }
	        
	        }catch(Exception e){
	            e.printStackTrace();
	        }        
	        
	        return resultUpdate;
	    }                                
}
