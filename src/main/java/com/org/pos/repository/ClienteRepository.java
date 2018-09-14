package com.org.pos.repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

@Repository
public class ClienteRepository {

	public int agregarCliente(String nombre,String apellidoP,String apellidoM,String dir,String tel){
	     int idCliente=0;
	     //DBConect conexion=new DBConect();
	        
	        try{
	            //Connection conexionMysql = conexion.GetConnection();

	            //Statement statement = conexionMysql.createStatement();

	            String varNombre=nombre;
	            String varPaterno=apellidoP;
	            String varMaterno=apellidoM;
	            String varDireccion=dir;
	            
	            String validadorVacios=varNombre+varPaterno+varMaterno+varDireccion;
	            
	            if(validadorVacios.equals("")){
	                
	                //JOptionPane.showMessageDialog(null,"No has proporcionado ningun dato");
	                
	                
	            }
	            
	            String mensajeError="";
	            
	            
	            if(varNombre.equals("")){
	                
	                mensajeError+="Nombre \n";
	                
	            }
	            
	            if(varPaterno.equals("")){
	                
	                mensajeError+="Apellido paterno \n";
	                
	            }
	            
	             if(varDireccion.equals("")){
	                
	                mensajeError+="Dirección \n";
	                
	            }

	            
	            if(!mensajeError.equals("")){
	            
	                mensajeError="Los siguientes campos son necesarios para continuar: \n\n "+mensajeError;
	                
	                //JOptionPane.showMessageDialog(null, mensajeError);
	                return 0; 
	            }
	              
	            
	            Date fecha =new Date();
	              
	            SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy/MM/dd");
	            
	            String fechaActual=formatoFecha.format(fecha);
	            
	            String sqlString="INSERT INTO `cliente` (`nombre`, `apellidop`, `apellidom`,`dirección`,`fechaDeRegistro`,`telefono`) "
	                    + " VALUES ('"+varNombre+"', '"+varPaterno+"', '"+varMaterno+"', '"+varDireccion+"','"+fechaActual+"','"+tel+"' )";
	        
	           // int resultado=statement.executeUpdate(sqlString);
	       
	            //if(resultado>0){

	                //JOptionPane.showMessageDialog(null, "El cliente se agrego correctamente");
	                
	            //}
	            
	            
	            String obtenerUltimaInsercion="Select MAX(idCliente) from cliente";
	            //ResultSet rs2 = statement.executeQuery(obtenerUltimaInsercion );
	            
	            //while(rs2.next()){
	            //    idCliente=rs2.getInt(1);
	            //}
	            
	           
	         
	       }catch(Exception e){
	           e.printStackTrace();
	       }   
	        
	       return idCliente; 

	}    
	
    private void BuscadorModificacionClienteActionPerformed(java.awt.event.ActionEvent evt) {                                                            
        
        //DBConect conexion=new DBConect();  
        
        try{

          //resultadoClientesEdicion= new DefaultTableModel();
          //tablaClientesEncontradosEdicion.setModel(resultadoClientesEdicion);
          Connection conexionMysql = null;//conexion.GetConnection();

          Statement statement = conexionMysql.createStatement();
          String nombreVar="";//NombreBusqueda2.getText();
          String apellidoPVar="";//apellidoBusqueda2.getText();
          String apellidoMVar="";//apellidoMBusqueda2.getText();

          String varBusqueda=nombreVar+apellidoMVar+apellidoPVar;

          if(varBusqueda.equals("")){
              //JOptionPane.showMessageDialog(null,"Se necesita al menos un campo para realizar la busqueda"); 
              return;
          }

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

          ResultSet rs = statement.executeQuery(sqlString);

          ResultSetMetaData rsMd = rs.getMetaData();
          //La cantidad de columnas que tiene la consulta
          int cantidadColumnas = rsMd.getColumnCount();
          //Establecer como cabezeras el nombre de las colimnas
          for (int i = 1; i <= cantidadColumnas; i++) {
           //resultadoClientesEdicion.addColumn(rsMd.getColumnLabel(i));
          }
          //Creando las filas para el JTable
          int resultados=0;
          while (rs.next()) {
           resultados++;
              Object[] fila = new Object[cantidadColumnas];
           for (int i = 0; i < cantidadColumnas; i++) {
             fila[i]=rs.getObject(i+1);
           }
           //resultadoClientesEdicion.addRow(fila);
          }
          
          if(resultados==0){
              //JOptionPane.showMessageDialog(null,"No se encontro un cliente con los datos proporcionados");
          }

        }catch(Exception e){
               e.printStackTrace();
        }       
    }
    
private void GuardarCambiosClienteActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        
        int fila=0;//tablaClientesEncontradosEdicion.getSelectedRow();
        int idClienteAModificar=0;//(int)tablaClientesEncontradosEdicion.getValueAt(fila, 0);
        String varNombre="";//newNombreVenta2.getText();
        String varApellidoP="";//newApellidoPVenta2.getText();
        String varApellidoM="";//newApellidoMVenta2.getText();
        String varDireccion="";//newDireccionVenta3.getText();
        String varTelefono="";//newTelVenta4.getText();

        //DBConect conexion=new DBConect();  
        
        try{

          Connection conexionMysql = null;//conexion.GetConnection();

          Statement statement = conexionMysql.createStatement();
          
          String sqlString="Update cliente set nombre='"+varNombre+"'"
                           +" , apellidoP='"+varApellidoP+"'"
                           +" , apellidoM='"+varApellidoM+"'"
                           +" , `dirección`='"+varDireccion+"'"
                           +" , telefono='"+varTelefono+"'"
                           +" where idCliente='"+idClienteAModificar+"'";
          
          int resultado=statement.executeUpdate(sqlString);
          if(resultado>=1){
             //JOptionPane.showMessageDialog(null,"Se modifico la información correctamente.");
          }else{
             //JOptionPane.showMessageDialog(null,"Ocurrio un error al guardar la información. \n Por favor intenta guardar cambios nuevamente");
          }
          
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        //actualizamos con los datos correspondientes
        //BuscadorModificacionClienteActionPerformed(evt);
        //panelModificarCliente.setVisible(false);
    }                                                    
	
	private void DesactivarClienteActionPerformed(java.awt.event.ActionEvent evt) {                                                  
	    
	    int fila=0;//tablaClientesEncontradosEdicion.getSelectedRowCount();
	   
	    if(fila==0){
	        //JOptionPane.showMessageDialog(null,"No has seleccionado un cliente para desactivarlo");
	        return;
	    }else{
	        fila=fila-1;
	    }
	    
	     int idClienteAModificar=0;//(int)tablaClientesEncontradosEdicion.getValueAt(fila, 0);
	     int confirmacion=0;
//	   int confirmacion= JOptionPane.showConfirmDialog(null,""
//	            + "Al desactivar un cliente, el cliente no será encontrado desde la venta,"
//	            + "el cliente no podra volver a activarse,\n será necesario crear un nuevo cliente en caso de ser necesario. \n"+
//	            "Las ventas realizadas a este cliente quedan guardadas en el historial.","¿Estas seguro que deseas desactivar el cliente?",JOptionPane.YES_NO_OPTION);
	   if(confirmacion==0){
	       //DBConect conexion=new DBConect();  
	    
	    try{
	
	      Connection conexionMysql = null;//conexion.GetConnection();
	
	      Statement statement = conexionMysql.createStatement();
	      
	      String sqlString="Update cliente set activo=0"
	                       +" where idCliente='"+idClienteAModificar+"'";
	      
	      int resultado=statement.executeUpdate(sqlString);
	      if(resultado>=1){
	         //JOptionPane.showMessageDialog(null,"Se desactivo el cliente, ya no se podran realizar ventas a este cliente");
	      }else{
	         //JOptionPane.showMessageDialog(null,"Ocurrio un error al desactivar el cliente la información. \n Por favor intentalo nuevamente");
	      }
	      
	    }catch(Exception e){
	        e.printStackTrace();
	    }
	   }else{
	   
	   }
	   
	}                                        
}
