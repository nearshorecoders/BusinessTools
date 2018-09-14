package com.org.pos.repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import com.mysql.jdbc.Connection;

public class CajaRepository {

	public void obtenerGastosDelDia(String fi,String ff){
        
        String sqlString="select consecutivo,cantidad,descripcion as motivo,NombreCompleto as 'retiró' from " +
                         " retiro a inner join usuarios b on a.usuario=b.idUsuario   where (a.fecha BETWEEN '"+fi+"' AND '"+ff+"')";
        System.out.println(sqlString);
        //DBConect conexion=new DBConect();  

        Connection conexionMysql=null;//conexion.GetConnection();
    
    try{
        
      Statement statement = conexionMysql.createStatement();
      //modeloRetiro= new DefaultTableModel();
      ResultSet rs = statement.executeQuery(sqlString);
      
      Double totalGastoD=0.0;
      while(rs.next()){
          totalGastoD+=rs.getDouble("cantidad");
      }
      rs.beforeFirst();
      //totalGasto.setText("$ "+totalGastoD);
      Double pretotal=calcularTotalVentasCaja(fi,ff,0.0);
      Double operacionBalance=pretotal-totalGastoD;
      //totalBalance.setText("$ "+operacionBalance);
      
      ResultSetMetaData rsMd = rs.getMetaData();
      //La cantidad de columnas que tiene la consulta
      int cantidadColumnas = rsMd.getColumnCount();
      //Establecer como cabezeras el nombre de las colimnas
      for (int i = 1; i <= cantidadColumnas; i++) {
      // modeloRetiro.addColumn(rsMd.getColumnLabel(i));
      }
      //Creando las filas para el JTable
      while (rs.next()) {
             Object[] fila = new Object[cantidadColumnas];
             for (int i = 0; i < cantidadColumnas; i++) {
               fila[i]=rs.getObject(i+1);
             }
        //     modeloRetiro.addRow(fila);
      }
      //listadoRetiro.setModel(modeloRetiro);
      
      
    }catch(Exception e){
        e.printStackTrace();
    }
  }
	 public Double calcularTotalVentasCaja(String fi,String ff,Double ivaConfigurado){
	        String fechaInicial=fi;
	        String fechaFinal=ff;
	        //DBConect conexion=new DBConect();
	                    Double sumaTotalPeriodo=0.0;
	        try{
	            Connection conexionMysql = null;//conexion.GetConnection();

	            Statement statement = conexionMysql.createStatement();
	            //DateEditor de=(DateEditor)jSpinner1.getEditor();
	            //DateEditor de2=(DateEditor)jSpinner2.getEditor();
	            String tiempoInicio=fechaInicial+" ";//+de.getFormat().format(jSpinner1.getValue());
	            String tiempoFin=fechaFinal+" ";//+de2.getFormat().format(jSpinner2.getValue());
	            
	            
	            String SQLString="SELECT a.idVenta as 'No. de venta',a.total,a.fechaVenta,b.descripcionProd,b.precioTotal,b.cantidad as 'Cantidad vendida', "+
	                             " b.precioTotal*b.cantidad as 'Subtotal',c.NombreCompleto as 'Vendido por', "+
	                             " c.NombreUsuario FROM venta as a inner join detalleventa b on a.idVenta=b.Venta_idVenta "+
	                             " inner join usuarios as c on c.idUsuario=a.usuarios_idUsuario "+
	                             " where a.fechaVenta BETWEEN '"+tiempoInicio+"' AND '"+tiempoFin+"' ";
	            
	            String usuarioSeleccionado="";//listaTodosLosUsuarios.getSelectedItem().toString();
	            if(usuarioSeleccionado.equals("---")){        
	                 SQLString+=" order by a.idventa,b.consecutivoVenta";
	            }else{
	                
	                 SQLString+=" and c.NombreCompleto='"+usuarioSeleccionado+"' ";
	                
	                 SQLString+=" order by a.idventa,b.consecutivoVenta";
	                 
	            }        
	           
	            
	            ResultSet rs= statement.executeQuery(SQLString);
	            

	            String nombreUsuario="---";
	            Double sumaIvaPeriodo=0.0;
	            while(rs.next()){
	               int cantidad=rs.getInt("Cantidad vendida");
	                if(cantidad>1){
	                    sumaTotalPeriodo+=(rs.getDouble("precioTotal")*cantidad);
	                    sumaIvaPeriodo+=(rs.getDouble("precioTotal")*cantidad)*ivaConfigurado;
	                }else{
	                    sumaTotalPeriodo+=rs.getDouble("precioTotal");
	                    sumaIvaPeriodo+=rs.getDouble("precioTotal")*ivaConfigurado;
	                } 
	                
//	                int cantidad=rs.getInt("cantidad");
//	                if(cantidad>1){
//	                     sumaTotalPeriodo+=(rs.getDouble("precioTotal")*cantidad);
//	                    
//	                }else{
//	                    sumaTotalPeriodo+=rs.getDouble("precioTotal");
//	                }
//	                
//	                nombreUsuario=rs.getString("Vendido por");
//	                sumaIvaPeriodo+=rs.getDouble("precioTotal")*ivaConfigurado;
	            }
	            
	            //totalVenta.setText("$ "+sumaTotalPeriodo);
	            
	            
	        }catch(Exception e){
	        
	        }
	        return sumaTotalPeriodo;
	    }
	 private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
	        
	        int gastos=0;//listadoRetiro.getRowCount();
	        
	        if(gastos==0){
	            //JOptionPane.showMessageDialog(null,"Si no hubo gastos por favor agregue un gasto con cantidad CERO\nparapoder realizar el corte de caja.");
	            return;
	        }
	        
	        //DBConect conexion=new DBConect(); 
	        Connection conexionMysql = null;//conexion.GetConnection();
	        
	        /////obtenemos el maximo ingresado en la caja
	         String sqlString="Select max(idCaja),inicioDelDia,finalDelDia,fecha from caja ";
	        try{
	         
	         Statement statement=conexionMysql.createStatement();
	         ResultSet rs2 = statement.executeQuery(sqlString);
	         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	         int idCaja=0;
	            while(rs2.next()){
	                idCaja=rs2.getInt(1);
	            }
	            
	            if(idCaja!=0){        
	                
	                String balance="";//totalBalance.getText();
	                balance=balance.replace("$", "");
	                balance=balance.trim();
	                String sqlString2="UPDATE CAJA set finalDelDia="+balance+ " WHERE idCaja="+idCaja;
	                statement.executeUpdate(sqlString2);
	            
	            }
	        
	        }catch(Exception e){
	            e.printStackTrace();
	        }        
	        
	        
	    }                                
}
