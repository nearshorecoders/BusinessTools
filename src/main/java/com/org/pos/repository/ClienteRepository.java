package com.org.pos.repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
    public Map<String, Object> listarClientes(Integer idLoguedUser) {
        try {
        	String query = "SELECT b.* FROM clientes where idcliente = ?";
        	
        	List<Map<String, Object>> list = jdbcTemplate.queryForList(query,new Object[]{idLoguedUser});
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("listaClientes", list);
        	
        	return map;
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

          String sqlString="Select idcliente as 'Cliente',concat(nombre,' ',apellidop,' ',apellidom) as 'Nombre completo',dirección as 'Dirección', " +
                           " telefono as 'Teléfono' " +
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
          

          //jdbcTemplate.query(sqlString, rse);
          

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
