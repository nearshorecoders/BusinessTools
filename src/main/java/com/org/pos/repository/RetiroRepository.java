package com.org.pos.repository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.jdbc.Connection;

public class RetiroRepository {
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt,String usuarioLogueado) {                                         
        
        Date fechaInicioDia=new Date();
          fechaInicioDia.setHours(01);
          fechaInicioDia.setMinutes(00);
          fechaInicioDia.setSeconds(00);
          Date fechaFinDia=new Date();
          fechaFinDia.setHours(23);
          fechaFinDia.setMinutes(59);
          fechaFinDia.setSeconds(59);
          
          SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          String fi=sf.format(fechaInicioDia);
          String ff=sf.format(fechaFinDia);
        
        
        //DBConect conexion=new DBConect(); 
        Connection conexionMysql = null;//conexion.GetConnection();
        
        String cantidad="";//cantidadRetiro.getText();
        String retiro="";//motivoRetiro.getText();
        if(cantidad.equals("") && retiro.equals("")){
            
            //JOptionPane.showMessageDialog(null,"Los datos necesarios para realizar la operación estan vacios,\n por lo cual no se abrira la caja.");
            return;
        }
        
        try{
            Statement statement = conexionMysql.createStatement();
            
            
            String getMax="select MAX(consecutivo) as max from " +
                           " retiro where (fecha BETWEEN '"+fi+"' AND '"+ff+"')";
             ResultSet rs=statement.executeQuery(getMax);
            int max=0;
             while(rs.next()){
            
                max=rs.getInt(1);
                
            }
            max=max+1;
             
            String sqlString2="insert into retiro(cantidad,descripcion,consecutivo,usuario) VALUES("+cantidad+",'"+retiro+"',"+max+","+usuarioLogueado+")";
            
            statement.executeUpdate(sqlString2);
//            Ticket t=new Ticket();
//            t.AbrirCaja();
//            obtenerGastosDelDia(fi,ff);
//            motivoRetiro.setText("");
//            cantidadRetiro.setText("");
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }    
}
