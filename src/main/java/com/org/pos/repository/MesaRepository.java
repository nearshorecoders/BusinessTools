package com.org.pos.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.org.pos.model.Cliente;
import com.org.pos.model.Mesa;

public class MesaRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("exchangeDS")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
    
    @Transactional(readOnly=false)
	public int agregarMesa(String numero,String ubicacion,String personas,String sucursal){
	     int idCliente=0;
	        
	        try{

	            String varNumero=numero;
	            String varUbicacion=ubicacion;
	            String varPersonas=personas;
	            String varSucursal=sucursal;
	            String varStatus="1";
	            
	            Date fecha =new Date();
	              
	            SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy/MM/dd");
	            
	            String fechaActual=formatoFecha.format(fecha);
	            
	            String sqlString="INSERT INTO `mesa` (`numero`, `ubicacion`, `personas`,`sucursal_idsucursal`,`statusMesa_idstatusMesa`) "
	                    + " VALUES ('"+varNumero+"', '"+varUbicacion+"', '"+varPersonas+"', '"+varSucursal+"','"+varStatus+"' )";
	        
	            int resultado=jdbcTemplate.update(sqlString);
	       
	            if(resultado>0){
		            String obtenerUltimaInsercion="Select MAX(idmesa) from mesa";
		           
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
        	String query = "SELECT idcliente FROM cliente ";
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
    
    public List<Mesa> buscarMesasTodas(String sucursal) {                                                            
    	List<Mesa> listaMesas=new ArrayList<Mesa>();
        try{
          
          String varSucursal=sucursal;

          String sqlString="Select idmesa,numero,ubicacion,personas,statusMesa_idstatusMesa" +
                           " from mesa where sucursal_idsucursal="+ varSucursal+";";

          Mesa mesa=null;
          try{
             
              List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlString);
              	for (Map row : rows) {
              		mesa=new Mesa();
              		mesa.setIdMesa(row.get("idmesa")!=null ? (String) row.get("idmesa") :"");
              		mesa.setNumero(row.get("numero")!=null ? (String) row.get("numero") :"");
              		mesa.setUbicacion(row.get("ubicacion")!=null ? (String) row.get("ubicacion") :"");
              		mesa.setPersonas(row.get("personas")!=null ? (String) row.get("personas") :"");
              		mesa.setStatus(row.get("statusMesa_idstatusMesa")!=null ? (String) row.get("statusMesa_idstatusMesa") :"");
              		listaMesas.add(mesa);
              	}

              	return listaMesas;
            }catch(Exception e){
                e.printStackTrace();
            }
          

        }catch(Exception e){
               e.printStackTrace();
        } 
        
        return listaMesas;
    }
    
    public List<Mesa> buscarMesasPorLimpiar(String sucursal) {                                                            
    	List<Mesa> listaMesas=new ArrayList<Mesa>();
        try{
          
          String varSucursal=sucursal;
          //status3= por limpiar
          String sqlString="Select idmesa,numero,ubicacion,personas,statusMesa_idstatusMesa" +
                           " from mesa where sucursal_idsucursal="+ varSucursal+" and statusMesa_idstatusMesa=3 ;";

          Mesa mesa=null;
          try{
             
              List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlString);
              	for (Map row : rows) {
              		mesa=new Mesa();
              		mesa.setIdMesa(row.get("idmesa")!=null ? (String) row.get("idmesa") :"");
              		mesa.setNumero(row.get("numero")!=null ? (String) row.get("numero") :"");
              		mesa.setUbicacion(row.get("ubicacion")!=null ? (String) row.get("ubicacion") :"");
              		mesa.setPersonas(row.get("personas")!=null ? (String) row.get("personas") :"");
              		mesa.setStatus(row.get("statusMesa_idstatusMesa")!=null ? (String) row.get("statusMesa_idstatusMesa") :"");
              		listaMesas.add(mesa);
              	}

              	return listaMesas;
            }catch(Exception e){
                e.printStackTrace();
            }
          

        }catch(Exception e){
               e.printStackTrace();
        } 
        
        return listaMesas;
    }
    
    
    public List<Mesa> buscarMesasLibres(String sucursal) {                                                            
        
    	List<Mesa> listaMesas=new ArrayList<Mesa>();
        try{
          
          String varSucursal=sucursal;
          //status1= libre
          String sqlString="Select idmesa,numero,ubicacion,personas,statusMesa_idstatusMesa" +
                           " from mesa where sucursal_idsucursal="+ varSucursal+" and statusMesa_idstatusMesa=1 ;";

          Mesa mesa=null;
          try{
             
              List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlString);
              	for (Map row : rows) {
              		mesa=new Mesa();
              		mesa.setIdMesa(row.get("idmesa")!=null ? (String) row.get("idmesa") :"");
              		mesa.setNumero(row.get("numero")!=null ? (String) row.get("numero") :"");
              		mesa.setUbicacion(row.get("ubicacion")!=null ? (String) row.get("ubicacion") :"");
              		mesa.setPersonas(row.get("personas")!=null ? (String) row.get("personas") :"");
              		mesa.setStatus(row.get("statusMesa_idstatusMesa")!=null ? (String) row.get("statusMesa_idstatusMesa") :"");
              		listaMesas.add(mesa);
              	}

              	return listaMesas;
            }catch(Exception e){
                e.printStackTrace();
            }
          

        }catch(Exception e){
               e.printStackTrace();
        } 
        
        return listaMesas;
    }
    public List<Mesa> buscarMesasReservadas(String sucursal) {                                                            
    	List<Mesa> listaMesas=new ArrayList<Mesa>();
        try{
          
          String varSucursal=sucursal;
          //status4= reservada
          String sqlString="Select idmesa,numero,ubicacion,personas,statusMesa_idstatusMesa" +
                           " from mesa where sucursal_idsucursal="+ varSucursal+" and statusMesa_idstatusMesa=4 ;";

          Mesa mesa=null;
          try{
             
              List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlString);
              	for (Map row : rows) {
              		mesa=new Mesa();
              		mesa.setIdMesa(row.get("idmesa")!=null ? (String) row.get("idmesa") :"");
              		mesa.setNumero(row.get("numero")!=null ? (String) row.get("numero") :"");
              		mesa.setUbicacion(row.get("ubicacion")!=null ? (String) row.get("ubicacion") :"");
              		mesa.setPersonas(row.get("personas")!=null ? (String) row.get("personas") :"");
              		mesa.setStatus(row.get("statusMesa_idstatusMesa")!=null ? (String) row.get("statusMesa_idstatusMesa") :"");
              		listaMesas.add(mesa);
              	}

              	return listaMesas;
            }catch(Exception e){
                e.printStackTrace();
            }
          

        }catch(Exception e){
               e.printStackTrace();
        } 
        
        return listaMesas;
    }  
    public List<Mesa> buscarMesasOcupadas(String sucursal) {                                                            
        
    	List<Mesa> listaMesas=new ArrayList<Mesa>();
        try{
          
          String varSucursal=sucursal;
          //status2= ocupadas
          String sqlString="Select idmesa,numero,ubicacion,personas,statusMesa_idstatusMesa" +
                           " from mesa where sucursal_idsucursal="+ varSucursal+" and statusMesa_idstatusMesa=2 ;";

          Mesa mesa=null;
          try{
             
              List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlString);
              	for (Map row : rows) {
              		mesa=new Mesa();
              		mesa.setIdMesa(row.get("idmesa")!=null ? (String) row.get("idmesa") :"");
              		mesa.setNumero(row.get("numero")!=null ? (String) row.get("numero") :"");
              		mesa.setUbicacion(row.get("ubicacion")!=null ? (String) row.get("ubicacion") :"");
              		mesa.setPersonas(row.get("personas")!=null ? (String) row.get("personas") :"");
              		mesa.setStatus(row.get("statusMesa_idstatusMesa")!=null ? (String) row.get("statusMesa_idstatusMesa") :"");
              		listaMesas.add(mesa);
              	}

              	return listaMesas;
            }catch(Exception e){
                e.printStackTrace();
            }
          

        }catch(Exception e){
               e.printStackTrace();
        } 
        
        return listaMesas;
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
