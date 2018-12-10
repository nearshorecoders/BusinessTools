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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.Connection;
import com.org.pos.model.Cliente;
import com.org.pos.model.Productos;

@Repository
public class ClienteRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("exchangeDS")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
    
    @Transactional(readOnly=false)
	public int agregarCliente(String nombre,String apellidoP,String apellidoM,String dir,String tel){
	     int idCliente=0;
	        
	        try{

	            String varNombre=nombre;
	            String varPaterno=apellidoP;
	            String varMaterno=apellidoM;
	            String varDireccion=dir;
	            
	            Date fecha =new Date();
	              
	            SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy/MM/dd");
	            
	            String fechaActual=formatoFecha.format(fecha);
	            
	            String sqlString="INSERT INTO `cliente` (`nombre`, `apellidop`, `apellidom`,`dirección`,`fechaDeRegistro`,`telefono`) "
	                    + " VALUES ('"+varNombre+"', '"+varPaterno+"', '"+varMaterno+"', '"+varDireccion+"','"+fechaActual+"','"+tel+"' )";
	        
	            int resultado=jdbcTemplate.update(sqlString);
	       
	            if(resultado>0){
		            String obtenerUltimaInsercion="Select MAX(idCliente) from cliente";
		           
		            Integer maxCreated=jdbcTemplate.queryForObject(obtenerUltimaInsercion,Integer.class);
		            return maxCreated;
	            }  
	         
	       }catch(Exception e){
	           e.printStackTrace();
	       }   
	        
	       return idCliente; 

	}    
	
    
    @Transactional(readOnly=true)
    public List<Cliente> listarClientes(Integer idLoguedUser) {
        try {
        	//listar solo clintes del usuario logueado
        	String query = "SELECT idcliente,nombre,apellidop,apellidom,dirección,telefono,activo,fechaDeRegistro FROM cliente ";
        	///corregir consulta
        	List<Cliente> clientes = new ArrayList<Cliente>();
        
        	List<Map<String, Object>> map = jdbcTemplate.queryForList(query);
        	for (Map row : map) {
        		Cliente cliente = new Cliente();
        		cliente.setIdClienteAModificar((Integer)(row.get("idcliente")));
        		cliente.setVarNombre((String)(row.get("nombre")));
        		cliente.setVarApellidoP((String)(row.get("apellidop")));
        		cliente.setVarApellidoM((String)(row.get("apellidom")));
        		cliente.setVarDireccion((String)(row.get("dirección")));
        		cliente.setVarTelefono((String)(row.get("telefono")));
        		cliente.setActivo((Integer)(row.get("activo")));
        		cliente.setFechaDeRegistro((Date)(row.get("fechaDeRegistro")));
        		clientes.add(cliente);
        		
        	}  

        	return clientes;
        }catch (Exception e){
        	LOGGER.error("Error", e);
            throw e;
        }
    	
    }
    
    public Cliente buscarCliente(String nombre, String apellidoP, String apellidoM) {                                                            
        
        try{

          String nombreVar=nombre;
          String apellidoPVar=apellidoP;
          String apellidoMVar=apellidoM;

          String varBusqueda=nombreVar+apellidoMVar+apellidoPVar;

          String sqlString="Select idcliente as 'Cliente',concat(nombre,' ',apellidop,' ',apellidom) as 'nombreCompleto',dirección as 'Direccion', " +
                           " telefono as 'telefono' " +
                           " from cliente where ";

          int indicadorAnd=0;

          if(!nombreVar.equals("")){
              sqlString+=" nombre like '"+nombreVar+"%'";
              indicadorAnd++;
          }

          if(!apellidoPVar.equals("")){
              if(indicadorAnd>0){
                  sqlString+=" and ";
              } 
              sqlString+=" apellidoP like '"+apellidoPVar+"%'";
               indicadorAnd++;
          }

          if(!apellidoMVar.equals("")){

              if(indicadorAnd>0){
                  sqlString+=" and ";
              }
              sqlString+=" apellidoM like '"+apellidoMVar+"%'";
              indicadorAnd++;
          }
          
          Cliente cliente=null;
          try{
             
              List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlString);
              	for (Map row : rows) {
              		cliente=new Cliente();
              		cliente.setVarNombre(row.get("nombreCompleto")!=null?(String) row.get("nombreCompleto"):"");
              		cliente.setVarDireccion(row.get("Direccion")!=null ? (String) row.get("Direccion") :"");
              		cliente.setVarTelefono(row.get("telefono")!=null ? (String) row.get("telefono") :"");
              		
              	}

              	return cliente;
            }catch(Exception e){
                e.printStackTrace();
            }
          

        }catch(Exception e){
               e.printStackTrace();
        } 
        
        return null;
    }
    
public Integer modificarCliente(Cliente cliente) {   

		Integer idClienteAModificar=cliente.getIdClienteAModificar();
		String varNombre=cliente.getVarNombre();
		String varApellidoP=cliente.getVarApellidoP(); 
		String varApellidoM=cliente.getVarApellidoM();
		String varDireccion=cliente.getVarDireccion(); 
		String varTelefono=cliente.getVarTelefono();
	
        Integer resultado=0;
        try{
          String sqlString="Update cliente set nombre='"+varNombre+"'"
                           +" , apellidoP='"+varApellidoP+"'"
                           +" , apellidoM='"+varApellidoM+"'"
                           +" , `dirección`='"+varDireccion+"'"
                           +" , telefono='"+varTelefono+"'"
                           +" where idCliente='"+idClienteAModificar+"'";
          
         resultado=jdbcTemplate.update(sqlString);

        }catch(Exception e){
            e.printStackTrace();
        }
        
        return resultado;
    }                                                    
	
	public Integer desactivarCliente(Integer idCliente) {                                                  
	    Integer resultado=0;
	    try{
	
	      String sqlString="Update cliente set activo=0"
	                       +" where idCliente='"+idCliente+"'";
	      
	      resultado=jdbcTemplate.update(sqlString);
	      
	      return resultado;
	      
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	    
	    return resultado;
	}                                        
}
