package com.org.pos.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
//import ppv.dbConection.DBConect;

/**
 *
 * @author user2
 */
public class Utils {
    
    Date fechaInicioDia=new Date();
    Date fechaFinDia=new Date();      
    SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         
    public String obtenerFechaInicioDia(){
        
        fechaInicioDia.setHours(01);
          fechaInicioDia.setMinutes(00);
          fechaInicioDia.setSeconds(00);
           String fi=sf.format(fechaInicioDia);
        return fi;
    }
    
     public String obtenerFechaFinDia(){
         fechaFinDia.setHours(23);
         fechaFinDia.setMinutes(59);
         fechaFinDia.setSeconds(59);
         String ff=sf.format(fechaFinDia);
         
        return ff;
    }
    
    public String  obtenerBetweenParaConsulta(String nombreCampoFecha){
    
        fechaInicioDia.setHours(01);
        fechaInicioDia.setMinutes(00);
        fechaInicioDia.setSeconds(00);
        String fi=sf.format(fechaInicioDia);
           
        fechaFinDia.setHours(23);
        fechaFinDia.setMinutes(59);
        fechaFinDia.setSeconds(59);
        String ff=sf.format(fechaFinDia);
    
        
        String between=" ("+nombreCampoFecha+" BETWEEN '"+fi+"' AND '"+ff+"')";
        
        return between;
    }
    
    public String obtenerNombreCompletoCliente(int idCliente){
        String udFueAtendidoPor="";
       // DBConect conexion=new DBConect();  
        Connection conexionMysql =null;
        		//conexion.GetConnection();          
       
        ///obtenemos el nombre completo del usuario para insertarlo al ticket
        try{
            
            Statement statement = conexionMysql.createStatement();

            String sqlString="select * from cliente where idCliente="+idCliente;

            ResultSet rs2=statement.executeQuery(sqlString);

            while(rs2.next()){
                  udFueAtendidoPor=rs2.getString("nombre")+" "+rs2.getString("apellidom")+" "+rs2.getString("apellidop");
            }
            
            /// si el cliente es 1 
            if(idCliente==1){
                udFueAtendidoPor="Cliente en local";
                
            }
            
        }catch(Exception e){
            e.printStackTrace();                  
        }
     
    return udFueAtendidoPor;
    }
    
    public String obtenerNombreCompletoUsuarioDelSistema(int idUsuario){
        String udFueAtendidoPor="";
        //DBConect conexion=new DBConect();  
        Connection conexionMysql = null;
        		// conexion.GetConnection();          
       
        ///obtenemos el nombre completo del usuario para insertarlo al ticket
        try{
            
            Statement statement = conexionMysql.createStatement();

            String sqlString="select nombreCompleto from usuarios where idUsuario="+idUsuario;

            ResultSet rs2=statement.executeQuery(sqlString);

            while(rs2.next()){
                  udFueAtendidoPor=rs2.getString(1);
            }

        }catch(Exception e){
            e.printStackTrace();                  
        }
     
    return udFueAtendidoPor;
    }
    
    
    public JTable obtenerDetalleCompra(int idVenta){
        //DBConect conexion=new DBConect();  
        JTable tabla=new JTable();
        DefaultTableModel resultadoClientesEdicion= new DefaultTableModel();
           try{

             Connection conexionMysql = null;
            		 //conexion.GetConnection();

             Statement statement = conexionMysql.createStatement();

             String sqlString="Select consecutivoVenta,cantidad,descripcionProd,precioTotal,(cantidad*precioTotal) as 'subtotal',Productos_idProductos,tamanio from detalleVenta where Venta_idVenta="+idVenta+" order by consecutivoVenta";

             ResultSet rs = statement.executeQuery(sqlString);

             ResultSetMetaData rsMd = rs.getMetaData();
             //La cantidad de columnas que tiene la consulta
             int cantidadColumnas = rsMd.getColumnCount();
             //Establecer como cabezeras el nombre de las colimnas
             for (int i = 1; i <= cantidadColumnas; i++) {
              resultadoClientesEdicion.addColumn(rsMd.getColumnLabel(i));
             }
             //Creando las filas para el JTable
             int resultados=0;
             while (rs.next()) {
              resultados++;
                 Object[] fila = new Object[cantidadColumnas];
              for (int i = 0; i < cantidadColumnas; i++) {
                fila[i]=rs.getObject(i+1);
              }
              resultadoClientesEdicion.addRow(fila);
             }

             if(resultados==0){
                 JOptionPane.showMessageDialog(null,"No se encontro un cliente con los datos proporcionados");
             }

       }catch(Exception e){
           e.printStackTrace();
       }
    
        tabla.setModel(resultadoClientesEdicion);
        return tabla;
       
    }

}
