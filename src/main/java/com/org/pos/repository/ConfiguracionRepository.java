package com.org.pos.repository;

import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;

@Repository
public class ConfiguracionRepository {

	 private void actualizarConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {                                                        
	        
	        //DBConect conexion=new DBConect();
	        
	        try{
	            Connection conexionMysql = null;//conexion.GetConnection();

	            Statement statement = conexionMysql.createStatement();
	            
	            String iva="";//+Double.parseDouble(valorIVA.getText())/100;
	            String d1="";//+Double.parseDouble(valorD1.getText())/100;
	            String d2="";//+Double.parseDouble(valorD2.getText())/100;
	            String d3="";//+Double.parseDouble(valorD3.getText())/100;
	            String d4="";//+Double.parseDouble(valorD4.getText())/100;
	            String d5="";//+Double.parseDouble(valorD5.getText())/100;
	            String sucursal="";//valorSucursal.getText();
	            String rfc="";//valorRFC.getText();
	            String slogan="";//valorSlogan.getText();
	            String direccion="";//valorDireccion.getText();
	            
	            
	            String sqlString="update installData set iva="+iva+", desc1="+d1+", desc2="+d2+", desc3="+d3+", desc4="+d4+", desc5="+d5+
	                    ",sucursal='"+sucursal+"',rfc='"+rfc+"',slogan='"+slogan+"',direccion='"+direccion+"' "
	                    + "where idInstallData=1";
	            
	            int result=statement.executeUpdate(sqlString);
	            
	            if(result>=1){
//	                JOptionPane.showMessageDialog(null,"Se guardo la configuración correctamente.");
//	                
//	                Configuracion c=obtenerConfiguracion();
//	                ivaConfigurado=c.getIva();
//	                valorIVA.setText(""+decimalEntero.format((ivaConfigurado*100)));
//	                valorD1.setText(""+decimalEntero.format(c.getDescuento1()*100));
//	                valorD2.setText(""+decimalEntero.format(c.getDescuento2()*100));
//	                valorD3.setText(""+decimalEntero.format(c.getDescuento3()*100));
//	                valorD4.setText(""+decimalEntero.format(c.getDescuento4()*100));
//	                valorD5.setText(""+decimalEntero.format(c.getDescuento5()*100));
//	                valorSucursal.setText(c.getSucursal());
//	                valorDireccion.setText(c.getDireccion());
//	                valorRFC.setText(c.getRfc());
//	                valorSlogan.setText(c.getSlogan());
	            }else{
	                //JOptionPane.showMessageDialog(null,"Ocurrio un error al guardar la configuración, por favor intente nuevamente.");
	            }
	            
	        }catch(Exception e){
	            
	            e.printStackTrace();
	        }    
	        
	        
	        
	    }                                          
}
