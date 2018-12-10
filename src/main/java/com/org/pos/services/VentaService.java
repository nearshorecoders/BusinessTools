package com.org.pos.services;

import java.security.Principal;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.Connection;
import com.org.pos.model.Productos;
import com.org.pos.model.Venta;
import com.org.pos.repository.VentasRepository;

@Service
public class VentaService {

	@Autowired 
	VentasRepository ventasRepository;
	
    Productos productoParaCarrrito;
    private Productos productoParaCarrritoDesdeOtraVentana;
	
    public void agregarUsuarioSinRegistro(){
        
        Vector row = new Vector();
        row.add(1);
        row.add("Usuario sin registro");
        row.add("");
        row.add("");
        //tablaClientesEncontrados.setModel(resultadoClientes);
        //resultadoClientes.addColumn("Cliente");
        //resultadoClientes.addColumn("Nombre completo");
        //resultadoClientes.addColumn("Dirección");
        //resultadoClientes.addColumn("Teléfono");
        //resultadoClientes.addRow(row);
    
    }
	
	public Map<String,Object> insertarVenta(Principal principal,Venta venta){
		Map<String,Object> result=new HashMap<String,Object>();
		
		Integer resultadoInsert=ventasRepository.insertarVentaBD(venta);
		
		if(resultadoInsert>=1){
			venta.setConsecutivoVenta(resultadoInsert);
			result.put("insertedVenta", venta);
		}
		
		return result;
	}
    
    public void agregarProductoDesdeOtraVentana(Productos productoParaCarrritoDesdeOtraVentana){
        //DefaultTableModel model = (DefaultTableModel) tablaDetalleVenta.getModel();
        Vector row = new Vector();

            int filasTotales=0;//model.getRowCount();
            int nuevaFila=filasTotales+1;
            row.add(nuevaFila);
            row.add(productoParaCarrritoDesdeOtraVentana.getUnidadesEnCaja());
            row.add(productoParaCarrrito.getDescripcion());
            row.add(productoParaCarrrito.getPrecioVenta());
            String x=(String)row.get(1);
            Double y=(Double)row.get(3);

            Double cant=Double.parseDouble(x);
            Double precio=y;

            Double subtGrid=cant*precio;

            row.add(""+subtGrid);

            row.add(productoParaCarrrito.getId());
            
//            model.addRow(row);
//
//            tablaDetalleVenta.getColumn("Id producto").setWidth(0);
//            tablaDetalleVenta.getColumn("Id producto").setMinWidth(0);
//            tablaDetalleVenta.getColumn("Id producto").setMaxWidth(0);
//            tablaDetalleVenta.getColumn("Tamaño").setWidth(0);
//            tablaDetalleVenta.getColumn("Tamaño").setMinWidth(0);
//            tablaDetalleVenta.getColumn("Tamaño").setMaxWidth(0);
            
            String descuentoUnidades="Obtener decuento correspondiente";//comboDescuentos.getSelectedItem().toString();
            
            String descuentoDecimal=descuentoUnidades.replace("%","");
            Double valorDescuento=0.0;
            
            if(descuentoDecimal.equals("---")){
                
            }else{
                valorDescuento=Double.parseDouble(descuentoDecimal);
                 valorDescuento=valorDescuento/100;
            }
            
            double granTotal=0.0;
//            for(int i=0;i<model.getRowCount();i++){
//
//                Double val=(Double)model.getValueAt(i, 3);
//                String cantidad=(String)model.getValueAt(i, 1);
//                Double valNumeric=val;
//                Double cantidadNumeric=Double.parseDouble(cantidad);
//                Double numericTotal=valNumeric*cantidadNumeric;
//                granTotal+=numericTotal;
//            }
//            Double descuentoTotal=(granTotal)*valorDescuento;
//            ivaTotal.setText("$"+decimales.format(granTotal*ivaConfigurado));
//            etiquetaGranTotal.setText("$"+decimales.format(granTotal-descuentoTotal));
//            codigoBusqueda.setText("");
//            descripcionManual.setText("");
//            cantidadPizza.setText("1");
//            codigoBusqueda.setFocusable(true);
                
    }
    public void lectorCBFinalizoLectura(String codigoBusqueda, String descripcion){
        
        ///detenemos el timer para evitar que se reinicie cuando se esta agregando el producto
        //timer.stop();
    
        int tipoDeProductoAgregado=0;
        
        ///buscamos el producto enm la db
        //DBConect conexion=new DBConect();  
        
        try{

          Connection conexionMysql = null;
        		  //conexion.GetConnection();

          Statement statement = conexionMysql.createStatement();

          String codigoVar=codigoBusqueda;
          String descripcionVar=descripcion;
          
          String sqlString="Select * from  productos " +
                           " where estatus=0 and codigo='"+codigoVar+"'";
                           
          if(!descripcionVar.equals("")){
               sqlString+= " And descripcion like '%"+descripcionVar+"%'";
          }
          
          if(codigoVar.equals("") && !descripcionVar.equals("")){
              
              sqlString="SELECT * FROM productos where estatus=0 and descripcion like '%"+descripcionVar+"%'";
              
          }
          
          ///validamos si existe producto seleccionado de la ventana buscador de productos
          if(productoParaCarrritoDesdeOtraVentana!=null){
              sqlString="SELECT * FROM productos where estatus=0 and idProductos="+productoParaCarrritoDesdeOtraVentana.getId();
              //cantidadPizza.setText(""+productoParaCarrritoDesdeOtraVentana.getUnidadesEnCaja());

          }
          
          
          ResultSet rs = statement.executeQuery(sqlString); 
          int contador=0;  
          while (rs.next()) {
              
           contador++;
           
           productoParaCarrrito=new Productos();
           productoParaCarrrito.setId(rs.getInt("idProductos"));
           productoParaCarrrito.setCodigo(rs.getString("codigo"));
           productoParaCarrrito.setDescripcion(rs.getString("descripcion"));
           productoParaCarrrito.setEstatus(rs.getInt("estatus"));
           productoParaCarrrito.setPrecioCompra(rs.getDouble("precioUnitarioC"));
           productoParaCarrrito.setPrecioVenta(rs.getDouble("precioUnitarioV"));
           productoParaCarrrito.setPresentacion(rs.getString("presentacion"));
           productoParaCarrrito.setUnidadMedida(rs.getString("uMedida"));
           productoParaCarrrito.setUnidadesEnCaja(rs.getDouble("unidadesEnCaja"));
           
           tipoDeProductoAgregado=rs.getInt("TipoProducto");
           
           if(productoParaCarrrito.getDescripcion().trim().equals("1 kg carne arabe")){
               tipoDeProductoAgregado=5;
           }
           if(productoParaCarrrito.getDescripcion().trim().equals("1/2 kg carne arabe")){
               tipoDeProductoAgregado=5;
           }
           
           int activo=rs.getInt("activo");
           
           if(activo==0){
               //JOptionPane.showMessageDialog(null, "El producto buscado YA NO ESTA ACTIVO en el inventario.\n\nPara agregar un producto a la venta, debe estar activo");
               //cantidadPizza.setText("1");
               //codigoBusqueda.setText("");
               return;
           }
           
          }
          
          if(contador==0){
              //JOptionPane.showMessageDialog(null, "No existe un producto con el código proporcionado");
              //cantidadPizza.setText("1");
              //codigoBusqueda.setText("");
              return;
          }
          
          DefaultTableModel modelPre =null;
          //(DefaultTableModel) tablaDetalleVenta.getModel();
          int cantidadProductos=0;
          modelPre.getRowCount();
          
          Object[] content = new Object[cantidadProductos];
          Object[] cantidadProdBuscado = new Object[cantidadProductos];
            for (int i = 0; i < cantidadProductos; i++) {
                content[i] = modelPre.getValueAt(i, 5);
                cantidadProdBuscado[i]=modelPre.getValueAt(i,1);
            }
            
            
            Object value_to_find= productoParaCarrrito.getId();

                int totalParaAgregar=0;
                for(int i=0;i<content.length;i++){

                    if(value_to_find.equals(content[i])){
                        int filaDeProducto=Arrays.asList(content).indexOf(value_to_find);
                        String cantidadYaEnLista2=(String)cantidadProdBuscado[i];
                        int cantidadYaEnLista=Integer.parseInt(cantidadYaEnLista2);
                        totalParaAgregar+=cantidadYaEnLista;
                    }
                }
                int cantidadAvenderValidacion=Integer.parseInt("Cantidad a agregar");
                totalParaAgregar+=cantidadAvenderValidacion;

                if(totalParaAgregar>productoParaCarrrito.getUnidadesEnCaja()){
                    String mensaje="Solo existen "+productoParaCarrrito.getUnidadesEnCaja()+" unidades en almacen";
                    mensaje+=" del producto: \n "+productoParaCarrrito.getDescripcion()+" \n";
                    mensaje+=" por lo cual no se puede agregar la cantidad deseada";
                    //JOptionPane.showMessageDialog(null, mensaje);
                    //cantidadPizza.setText("1");
                    //codigoBusqueda.setText("");
                    return;
                }

          if(contador>0){
              
             int cantidadAvender=Integer.parseInt("cantidad");
             Double cantidadEnAlmacen=productoParaCarrrito.getUnidadesEnCaja();
             ///recorrer y sumar los productos con el mismo codigo o id 
             ///para obtener cuantos productos en total de cantidad se han agregado
             
             
             if(cantidadAvender>cantidadEnAlmacen){
                 
                 String mensaje="Solo existen "+cantidadEnAlmacen+" unidades en almacen";
                 mensaje+=" del producto: \n "+productoParaCarrrito.getDescripcion()+" \n";
                 mensaje+=" por lo cual no se puede vender la cantidad deseada";
                 //JOptionPane.showMessageDialog(null, mensaje);
                 
                 if(cantidadEnAlmacen>0){
                    //codigoBusqueda.setText("");
                    //cantidadPizza.setText(""+productoParaCarrrito.getUnidadesEnCaja());
                    //codigoBusqueda.setText(productoParaCarrrito.getCodigo());
                 }else{
                     //codigoBusqueda.setText("");
                     //cantidadPizza.setText("1");
                 }
                 
                 return;
             } 
              
            DefaultTableModel model = null;//(DefaultTableModel) tablaDetalleVenta.getModel();
            Vector row = new Vector();

            int filasTotales=model.getRowCount();
            int nuevaFila=filasTotales+1;
            row.add(nuevaFila);
            row.add("cantidad de producto");
            row.add(productoParaCarrrito.getDescripcion());
            row.add(productoParaCarrrito.getPrecioVenta());
            String x=(String)row.get(1);
            Double y=(Double)row.get(3);

            Double cant=Double.parseDouble(x);
            Double precio=y;

            Double subtGrid=cant*precio;

            row.add(""+subtGrid);

            row.add(productoParaCarrrito.getId());
            row.add(" ");
            
            model.addRow(row);

//            tablaDetalleVenta.getColumn("Id producto").setWidth(0);
//            tablaDetalleVenta.getColumn("Id producto").setMinWidth(0);
//            tablaDetalleVenta.getColumn("Id producto").setMaxWidth(0);
//            tablaDetalleVenta.getColumn("Tamaño").setWidth(0);
//            tablaDetalleVenta.getColumn("Tamaño").setMinWidth(0);
//            tablaDetalleVenta.getColumn("Tamaño").setMaxWidth(0);
             ///obtenemos el descuento
            String descuentoUnidades="descuentos";
            //comboDescuentos.getSelectedItem().toString();
            
            String descuentoDecimal=descuentoUnidades.replace("%","");
            Double valorDescuento=0.0;
            
            if(descuentoDecimal.equals("---")){
                
            }else{
                valorDescuento=Double.parseDouble(descuentoDecimal);
                valorDescuento=valorDescuento/100;
            }
            
            double granTotal=0.0;
            for(int i=0;i<model.getRowCount();i++){

                Double val=(Double)model.getValueAt(i, 3);
                String cantidad=(String)model.getValueAt(i, 1);
                Double valNumeric=val;
                Double cantidadNumeric=Double.parseDouble(cantidad);
                Double numericTotal=valNumeric*cantidadNumeric;
                granTotal+=numericTotal;
            }
            Double descuentoTotal=granTotal*valorDescuento;
             
//            ivaTotal.setText("$"+decimales.format(granTotal*ivaConfigurado));
//            etiquetaGranTotal.setText("$"+decimales.format((granTotal-descuentoTotal)));
//            codigoBusqueda.setText("");
//            descripcionManual.setText("");
//            cantidadPizza.setText("1");
//            codigoBusqueda.setFocusable(true);
            
            /////agregamos el producto a la lista de tipos para saber si imprimir ticket o no
            //tiposDeProductoAgregados.add(tipoDeProductoAgregado);
            
          }else{
//              JOptionPane.showMessageDialog(null,"No existe producto con el código proporcionado");
//              codigoBusqueda.setText("");
//              descripcionManual.setText("");
          }
          
        }catch(Exception e){
                e.printStackTrace();
        } 
        
    }
	
}
