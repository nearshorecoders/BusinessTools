package com.org.pos.repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;
import com.org.pos.model.Productos;
import com.org.pos.utils.Utils;

@Repository
public class VentasRepository {

    private void generarReporteVentasActionPerformed(java.awt.event.ActionEvent evt, Double ivaConfigurado) {                                                     
        Date fechaInit = null;//fechaInicio.getDate();
        Date fechaEnd = null;//fechaFin.getDate();
        Date fechaFin=null;
        if(fechaInit==null || fechaFin==null){
            //JOptionPane.showMessageDialog(null,"Es necesario proporcionar el periodo de tiempo del reporte");
            return;
        }
        
        if(fechaEnd.before(fechaInit)){
            //JOptionPane.showMessageDialog(null,"La fecha de fin no puede ser anterior a la fecha de inicio");
            return;
        }
        
        SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy/MM/dd");   
        String fechaInicial=formatoFecha.format(fechaInit);
        String fechaFinal=formatoFecha.format(fechaEnd);
        //DBConect conexion=new DBConect();
        
        try{
            Connection conexionMysql =null;// conexion.GetConnection();

            Statement statement = conexionMysql.createStatement();
            //DateEditor de=(DateEditor)jSpinner1.getEditor();
            //DateEditor de2=(DateEditor)jSpinner2.getEditor();
            String tiempoInicio=fechaInicial+" ";//+de.getFormat().format(jSpinner1.getValue());
            String tiempoFin=fechaFinal+" ";//+de2.getFormat().format(jSpinner2.getValue());
            
            
            String SQLString="SELECT a.consecutivoVenta as 'No. de venta',a.total,a.fechaVenta,b.descripcionProd,d.dirección as 'Dirección de entrega',b.precioTotal,b.cantidad as 'Cantidad vendida', "+
                             " b.precioTotal*b.cantidad as 'Subtotal',c.NombreCompleto as 'Vendido por', "+
                             " c.NombreUsuario FROM venta as a inner join detalleventa b on a.idVenta=b.Venta_idVenta "+
                             " inner join usuarios as c on c.idUsuario=a.usuarios_idUsuario "+
                             " inner join cliente as d on d.idCliente=a.cliente_idcliente"+
                             " where a.fechaVenta BETWEEN '"+tiempoInicio+"' AND '"+tiempoFin+"' ";
            
            String usuarioSeleccionado="";//listaTodosLosUsuarios.getSelectedItem().toString();
            if(usuarioSeleccionado.equals("---")){        
                 SQLString+=" order by a.idventa,b.consecutivoVenta";
            }else{
                
                 SQLString+=" and c.NombreCompleto='"+usuarioSeleccionado+"' ";
                
                 SQLString+=" order by a.idventa,b.consecutivoVenta";
                 
            }        
           
            System.out.println(SQLString);
            
            ResultSet rs= statement.executeQuery(SQLString);
            
            Double sumaTotalPeriodo=0.0;
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
                
                nombreUsuario=rs.getString("Vendido por");
               
            }
            
            if(sumaTotalPeriodo==0.0){
                //JOptionPane.showMessageDialog(null, "No se encontraron registros de venta en el periodo seleccionado");
            }
            
            if(usuarioSeleccionado.equals("---")){
            
            }else{
                //labelUsuarioQueVendio.setText(nombreUsuario);
            }
            
            //labelTotalPeriodo.setText("$"+decimales.format(sumaTotalPeriodo));
            //labelTotalIvaPeriodo.setText("$"+decimales.format(sumaIvaPeriodo));
            
            rs.beforeFirst();
            
            //DefaultTableModel listaDetalleVentasPeriodo= new DefaultTableModel();
                
                ResultSetMetaData rsMd = rs.getMetaData();
                //La cantidad de columnas que tiene la consulta
                int cantidadColumnas = rsMd.getColumnCount();
                //Establecer como cabezeras el nombre de las colimnas
                for (int i = 1; i <= cantidadColumnas; i++) {
                 //listaDetalleVentasPeriodo.addColumn(rsMd.getColumnLabel(i));
                }
                //Creando las filas para el JTable
                while (rs.next()) {
                 Object[] fila = new Object[cantidadColumnas];
                 for (int i = 0; i < cantidadColumnas; i++) {
                   fila[i]=rs.getObject(i+1);
                 }
                 //listaDetalleVentasPeriodo.addRow(fila);
                }
            
                //listaReporte.setModel(listaDetalleVentasPeriodo);

        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
    }    
    
    private void confirmarVentaBotonActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        
//        JTable jTableHelper = new JTable() {
//           private static final long serialVersionUID = 1;
//           public boolean isCellEditable(int row, int column) {                
//               return false;               
//           };
//       };
//       
//       jTableHelper.setModel(tablaDetalleVenta.getModel());
       
//       tablaDetalleVenta=jTableHelper;
       
       DefaultTableModel model =null;// (DefaultTableModel) tablaDetalleVenta.getModel();
       ////obtenemos el consecutivo de venta del día
       //DBConect conexion=new DBConect();  
       int consecutivoVenta=1;
       try{

         Connection conexionMysql = null;//conexion.GetConnection();

         Statement statement = conexionMysql.createStatement();
         
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
                 
         
         String sqlString="select max(consecutivoVenta) from Venta where (fechaVenta BETWEEN '"+fi+"' AND '"+ff+"')";
         System.out.println(sqlString);
         ResultSet rs=statement.executeQuery(sqlString);
         
         while(rs.next()){
             //consecutivoVenta=rs.getInt(1);
             //if(consecutivoVenta>1){
                 consecutivoVenta=rs.getInt(1)+1;
             //}
             
         }
         
       }catch(Exception e){
           e.printStackTrace();
       }
       
       //contador++;
       
       int noSeleccionados=0;//seleccionGlobalCliente;
       
       //tablaClientesEncontrados.getSelectedRowCount();
       
//       if (tablaClientesEncontrados.getCellSelectionEnabled()) {
//           //tablaClientesEncontrados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//           int rowIndex = tablaClientesEncontrados.getSelectedRow();
//           int colIndex = tablaClientesEncontrados.getSelectedColumn();
//           System.out.print(rowIndex+","+colIndex);
//
//       }
      
       if(noSeleccionados<=0){
           //JOptionPane.showMessageDialog(null,"Es necesario seleccionar un cliente para continuar");
           return;
       }
       
       
       String cliente="";//getClienteId();
       
       if(cliente.equals("")){
           //JOptionPane.showMessageDialog(null,"Es necesario seleccionar un cliente para continuar");
           return;
       }
       ////realizar insert en venta
               
      // DBConect conexion=new DBConect();
       
       try{
           Connection conexionMysql = null;//conexion.GetConnection();

           Statement statement = conexionMysql.createStatement();
           Statement statement2 = conexionMysql.createStatement();
           
           ///insertamos la cabecera de la venta
           int cantidadProductos=model.getRowCount();
           
           if(cantidadProductos<=0){
               
              //JOptionPane.showMessageDialog(null, "No has agregado ningun producto");
              return;
               
           }else{

              String precioSinSigno="";//etiquetaGranTotal.getText().substring(1,etiquetaGranTotal.getText().length());
              
              String efectivo="";//efectivoRecibido.getText();
              Double efectivoRecibido1=0.0;
              Double precioSinSigno1=0.0;
              
              
              if(efectivo.equals("")){
                  //JOptionPane.showMessageDialog(null, "Se necesita saber cuanto efectivo recibiste");
                  return;
              }else{
                  try{
                   precioSinSigno1=Double.parseDouble(precioSinSigno);
                   efectivoRecibido1=Double.parseDouble(efectivo);
                   
                  }catch(Exception e){
                      //JOptionPane.showMessageDialog(null, "No introdujiste un numero");
                      return;
                  }
                   if(efectivoRecibido1<precioSinSigno1){
                       //JOptionPane.showMessageDialog(null, "La cantidad recibida no puede ser menor al costo");
                       return;
                   }
              }
              
               String sqlString="INSERT INTO `venta` (`total`,`cliente_idcliente`,`usuarios_idusuario`,`consecutivoVenta`,`efectivoRecib`,`cambio`) "
                       + " VALUES ('"+precioSinSigno+"','"+1+"', '"+2+"',"+consecutivoVenta+","+efectivoRecibido1+","+(efectivoRecibido1-precioSinSigno1)+" )";

               int resultado=statement.executeUpdate(sqlString);
               
               Utils u=new Utils();
               
               String sqlMaxID="select max(consecutivoVenta) as max from Venta where "+u.obtenerBetweenParaConsulta("fechaVenta");
               
               ResultSet rs=statement.executeQuery(sqlMaxID);
               int ultimaVentaRealizada=1;
               while(rs.next()){
                   ultimaVentaRealizada=rs.getInt(1);
               }
               
               //if(ultimaVentaRealizada<1){
               //    ultimaVentaRealizada=1;
               //}
               
               
               ////obtenemos el max id de la ultima venta insertada
               String sqlMaxIDM="select max(idVenta) as max from Venta where "+u.obtenerBetweenParaConsulta("fechaVenta");
               
               ResultSet rsM=statement.executeQuery(sqlMaxIDM);
               int ultimaVentaRealizadaM=1;
               while(rsM.next()){
                   ultimaVentaRealizadaM=rsM.getInt(1);
               }
               
               if(ultimaVentaRealizadaM<1){
                   ultimaVentaRealizadaM=1;
               }
               
               
               ////Obtenemos el tamaño para el ticket
               //de la pizza
               String tamañoTicket="";
               if(resultado>0){
               
                   int errores=0;
                   for(int i=0;i<model.getRowCount();i++){
                   //insertamos el detalle de la venta
                       String consecutivo,cantidad,descripcion,precio,id;
                   
                           consecutivo=""+model.getValueAt(i,0);
                           cantidad=""+model.getValueAt(i,1);
                           descripcion=""+model.getValueAt(i,2);
                           precio=""+model.getValueAt(i,3);
                           id=""+model.getValueAt(i,5);
                           tamañoTicket=""+model.getValueAt(i,6);
                           
                           String sqlString2="INSERT INTO `detalleVenta` (`consecutivoVenta`,`cantidad`,`precioTotal`,`descripcionProd`,`Productos_idProductos`,`Venta_idVenta`,`tamanio`) "
                           + " VALUES ('"+consecutivo+"','"+cantidad+"', '"+precio+"','"+descripcion+"','"+id+"','"+ultimaVentaRealizadaM+"','"+tamañoTicket+"' )";
                       
                           int resultado2=statement2.executeUpdate(sqlString2);
                           
                           if(resultado2>0){
                               
                               String vendidos="select cantidadVendidos,unidadesEnCaja from productos where idProductos="+id;
               
                               ResultSet rsP=statement.executeQuery(vendidos);
                               int cantidadVend=0;
                               int cantidadAlm=0;
                               while(rsP.next()){
                                   cantidadVend=rsP.getInt(1);
                                   cantidadAlm=rsP.getInt(2);
                               }
                               ////Producto repository call
                               //marcarProductoComoUtilizadoEnVenta(id,""+cantidadAlm,""+cantidadVend,cantidad);
                               //if(indicadorRefrescoUtilizadoEnPaquete==1){                                
                               //    marcarRefrescoUtilizadoEnPaquete();
                               //}
                               
                               
                           }else{
                               errores++;
                           }
                   }
                   
                   if(errores==0){
                       
                       String itemsVenta="";//obtenerProductosParaTicket(tablaDetalleVenta);
                       
                       //JOptionPane.showMessageDialog(null,"Se genero correctamente la venta No. "+ultimaVentaRealizada);
                       DefaultTableModel modelLimpio=null;//(DefaultTableModel)tablaDetalleVenta.getModel();
                       modelLimpio.setNumRows(0);
                       
                       String udFueAtendidoPor="";
                       
                       ///obtenemos el nombre completo del usuario para insertarlo al ticket
                       try{

                           statement = conexionMysql.createStatement();
                           String usuarioLogueado="";
                           sqlString="select NombreCompleto from usuarios where idusuario="+usuarioLogueado;

                           ResultSet rs2=statement.executeQuery(sqlString);

                           while(rs2.next()){
                               udFueAtendidoPor=rs2.getString(1);
                           }

                         }catch(Exception e){
                             e.printStackTrace();
                         }

                       int fila=0;//tablaClientesEncontrados.getSelectedRow();
                       String clienteTicket="";//+tablaClientesEncontrados.getValueAt(fila, 1);
                       
                       String direccionCliente="";//+tablaClientesEncontrados.getValueAt(fila, 2);
                       String telefonoCliente="";
                       String preTelefono="";//+tablaClientesEncontrados.getValueAt(fila, 3);
                       
                       if(!preTelefono.equals("")){
                           telefonoCliente="\nTelefono del cliente:";//+tablaClientesEncontrados.getValueAt(fila, 3);
                           
                           direccionCliente+=telefonoCliente;
                       }
                       
                       
                       if(clienteTicket.equals("Usuario sin registro")){
                           clienteTicket="Cliente en local";
                           direccionCliente="-----------";
                           
                           String seleccion="";//seleccionPizza1.getSelectedItem().toString();
                           boolean tipoProdAgregado=true;//tiposDeProductoAgregados.contains(5);
                           
                           if(!seleccion.equals("---") || tipoProdAgregado==true){
                               do{
                                   clienteTicket="";//JOptionPane.showInputDialog(null,"¿Cual es el nombre del cliente?");
                               }while(clienteTicket.equals(""));
                           }
                           
                       }
                       ///damos el formato deseado a la hora y fecha
                       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
                       Date now = new Date();
                       String fecha=sdf.format(now);
                                    
                       Double ivaDouble=0.0;//Double.parseDouble(ivaTotal.getText().replace("$", ""));
                       Double totalDouble=0.0;//Double.parseDouble(etiquetaGranTotal.getText().replace("$", ""));
                       
                       Double subTotal=totalDouble-ivaDouble;
                       boolean tipoProdVendido=true;//tiposDeProductoAgregados.contains(0);
                       if(0==1 && tipoProdVendido==true){
                          ///No imprimimos ticket, solo abrimos la caja 
                          ///Ticket t=new Ticket();
                          //t.AbrirCaja();
                          //JOptionPane.showMessageDialog(null,"Abriendo caja por venta de rebanada");
                       }else{
                           ///imprimimos el ticket normalmente
                           
                           boolean tipoProd4=true;//;tiposDeProductoAgregados.contains(4);
                           
                           //obtenemos el refresco seleccionado en la interfaz
                           String refresco="";//jComboBox2.getSelectedItem().toString();
                           if(tipoProd4==true){
                               refresco="";
                           }else{                        
                               refresco="Refresco:"+refresco;
                           }
                           
//                           Ticket t=new Ticket(valorSucursal.getText(),valorDireccion.getText(),ultimaVentaRealizada+"",udFueAtendidoPor,fecha,itemsVenta,subTotal+"",ivaTotal.getText(),etiquetaGranTotal.getText(),efectivoRecibido.getText(),cambioTotal.getText(),clienteTicket,direccionCliente,refresco);
//                           imprimirLogo();
//                           t.Imprimir();
//                           JOptionPane.showConfirmDialog(null,"Se imprimira el segundo ticket","",JOptionPane.OK_OPTION);
//                           imprimirLogo();
//                           t.Imprimir();
                       }
                       
//                       resultadoClientes.setColumnCount(0);
//                       resultadoClientes.setRowCount(0);
//                       
//                     
//       
//                       resultadoClientes= new DefaultTableModel();
//                       tablaClientesEncontrados.setModel(resultadoClientes);
//                       etiquetaGranTotal.setText("---");
//                       efectivoRecibido.setText("");
//                       cambioTotal.setText("$");
//                       ivaTotal.setText("---");
//                       seleccionGlobalCliente=0;
//                       
//                       agregarUsuarioSinRegistro();
//                       
//                       seleccionPizza1.setSelectedIndex(0);
//                       seleccionPizza2.setSelectedIndex(0);
//                       seleccionPizza3.setSelectedIndex(0);
//                       seleccionExtras1.setSelectedIndex(0);
//                       seleccionExtras2.setSelectedIndex(0);
//                       
//                       tiposDeProductoAgregados.clear();
                   }
                   
               }
          
           }
      }catch(Exception e){
          e.printStackTrace();
      } 
       
   }                                                   

   public void imprimirLogo(){
   
//       PrintService service = PrintServiceLookup.lookupDefaultPrintService();
//       DocPrintJob job = service.createPrintJob();
//       DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
//       SimpleDoc doc = new SimpleDoc(new MyPrintable(), flavor, null);
//
//       PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
//       aset.add(OrientationRequested.PORTRAIT);
//       aset.add(MediaSizeName.INVOICE);
//       try {
//           job.print(doc,aset);
//       } catch (PrintException ex) {
//           Logger.getLogger(Venta.class.getName()).log(Level.SEVERE, null, ex);
//       }
   
   }
   public String obtenerProductosParaTicket(){
       
       String productos = "";
       
       //DefaultTableModel model = (DefaultTableModel) tabla.getModel();
       int filas =0;// model.getRowCount();

       int espacio1 = 3;
       int espacio2 = 29;
       int espacio3 = 3;

       int resta1 = 0;
       int resta2 = 0;
       String espacios1 = "";
       String espacios2 = "";

       String can = "";
       String pro = "";
       String pre = "";
       String tamanio="";
       
       for (int i = 0; i < filas; i++) {
            
//           ///aqui obtenemos la cantidad del producto
//           if (tabla.getValueAt(i, 1).toString().length() < espacio1) {
//                can = tabla.getValueAt(i, 1).toString();
//                resta1 = espacio1 - tabla.getValueAt(i, 1).toString().length();
//            } else {
//               can = tabla.getValueAt(i, 1).toString().substring(0, espacio1 - 1);
//
//            }
//            
//           ///aqui obtenemos la descripción del producto
//            if (tabla.getValueAt(i, 2).toString().length() < espacio2) {
//            
//                resta2 = espacio2 - tabla.getValueAt(i, 2).toString().length();
//                pro = tabla.getValueAt(i, 2).toString();
//                
//               boolean contains = pro.contains(",");
//                
//              if(contains==false){
//                  pro=pro+", ";
//              } 
//                
//               String[] split = pro.split(",");
//               
//               String compar="";
//               if(split.length==2){ 
//                   compar=split[1];
//                   compar=compar.trim();
//               }
//
//                if(compar.equals("Extras:---")){
//                    pro=pro.replace("Extras:---", "");
//                }else{
//                    if(compar.equals("Extras:Queso extra")){
//                        pro=pro.replace(compar, " Ext Q");
//                    }
//                    if(compar.equals("Extras:Extra general")){
//                        pro=pro.replace(compar, " Ext G");
//                    }
//                }
//                
//            } else {
//            
//                pro = tabla.getValueAt(i, 2).toString();//.substring(0, espacio2 - 1);
//                /////sistituimos la cadena de extras
//                String[] split = pro.split(",");
//               
//                String compar="";
//                if(split.length==2){ 
//                    compar=split[1];
//                    compar=compar.trim();
//                }
//
//                if(compar.equals("Extras:---")){
//                     pro=pro.replace("Extras:---", "");
//                }else{
//                    if(compar.equals("Extras:Queso extra")){
//                        pro=pro.replace(compar, " Ext Q");
//                    }
//                    if(compar.equals("Extras:Extra general")){
//                        pro=pro.replace(compar, " Ext G");
//                    }
//                }
//                
//                pro += " ";
//                
//            }
//            
//            if (tabla.getValueAt(i, 4).toString().length() < espacio3) {
//            
//                pre = tabla.getValueAt(i, 4).toString();
//                
//            } else {
//            
//                pre = tabla.getValueAt(i, 4).toString().substring(0, espacio3 - 1);
//                
//            }
//            
//            for (int j = 0; j < resta1; j++) {
//            
//                espacios1 += " ";
//
//            }
//            
//            for (int j = 0; j < resta2; j++) {
//            
//                espacios2 += " ";
//                
//            }
//            
//            
//            if (tabla.getValueAt(i, 6).toString().length() < espacio3) {
//            
//                tamanio = tabla.getValueAt(i, 6).toString();
//                
//            } 
            
            productos += can + espacios1 + pro + espacios2 +""+tamanio+" "+ pre + "\n";
            espacios1 = "";
            espacios2 = "";
            
       }
       
       return productos;
   }
   
   public void lectorCBFinalizoLectura(){
       
       ///detenemos el timer para evitar que se reinicie cuando se esta agregando el producto
       //timer.stop();
   
       int tipoDeProductoAgregado=0;
       
       ///buscamos el producto enm la db
      // DBConect conexion=new DBConect();  
       
       try{

         Connection conexionMysql = null;//conexion.GetConnection();

         Statement statement = conexionMysql.createStatement();

         String codigoVar="";//codigoBusqueda.getText();
         String descripcionVar="";//descripcionManual.getText();
         
         String sqlString="Select * from  productos " +
                          " where estatus=0 and codigo='"+codigoVar+"'";
                          
         if(!descripcionVar.equals("")){
              sqlString+= " And descripcion like '%"+descripcionVar+"%'";
         }
         
         if(codigoVar.equals("") && !descripcionVar.equals("")){
             
             sqlString="SELECT * FROM productos where estatus=0 and descripcion like '%"+descripcionVar+"%'";
             
         }
         
         ///validamos si existe producto seleccionado de la ventana buscador de productos
         if(null!=null){
             sqlString="SELECT * FROM productos where estatus=0 and idProductos=";//+productoParaCarrritoDesdeOtraVentana.getId();
             //cantidadPizza.setText(""+productoParaCarrritoDesdeOtraVentana.getUnidadesEnCaja());

         }
         
         
         ResultSet rs = statement.executeQuery(sqlString); 
         int contador=0;  
         while (rs.next()) {
             
          contador++;
          
          Productos productoParaCarrrito=new Productos();
          productoParaCarrrito.setId(""+rs.getInt("idProductos"));
          productoParaCarrrito.setCodigo(rs.getString("codigo"));
          productoParaCarrrito.setDescripcion(rs.getString("descripcion"));
          productoParaCarrrito.setEstatus(rs.getInt("estatus"));
          productoParaCarrrito.setPrecioCompra(rs.getDouble("precioUnitarioC"));
          productoParaCarrrito.setPrecioVenta(rs.getDouble("precioUnitarioV"));
          productoParaCarrrito.setPresentacion(rs.getString("presentacion"));
          productoParaCarrrito.setUnidadMedida(rs.getString("uMedida"));
          productoParaCarrrito.setUnidadesEnCaja(rs.getInt("unidadesEnCaja"));
          
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
         
         
         //DefaultTableModel modelPre = (DefaultTableModel) tablaDetalleVenta.getModel();
         int cantidadProductos=0;//modelPre.getRowCount();
         
         Object[] content = new Object[cantidadProductos];
         Object[] cantidadProdBuscado = new Object[cantidadProductos];
           for (int i = 0; i < cantidadProductos; i++) {
               //content[i] = modelPre.getValueAt(i, 5);
               //cantidadProdBuscado[i]=modelPre.getValueAt(i,1);
           }
           
           
           Object value_to_find= 1;//productoParaCarrrito.getId();

               int totalParaAgregar=0;
               for(int i=0;i<content.length;i++){

                   if(value_to_find.equals(content[i])){
                       int filaDeProducto=Arrays.asList(content).indexOf(value_to_find);
                       String cantidadYaEnLista2=(String)cantidadProdBuscado[i];
                       int cantidadYaEnLista=Integer.parseInt(cantidadYaEnLista2);
                       totalParaAgregar+=cantidadYaEnLista;
                   }
               }
               int cantidadAvenderValidacion=1;//Integer.parseInt(cantidadPizza.getText());
               totalParaAgregar+=cantidadAvenderValidacion;
               Productos productoParaCarrrito=new Productos();
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
             
            int cantidadAvender=0;//Integer.parseInt(cantidadPizza.getText());
            int cantidadEnAlmacen=productoParaCarrrito.getUnidadesEnCaja();
            ///recorrer y sumar los productos con el mismo codigo o id 
            ///para obtener cuantos productos en total de cantidad se han agregado
            
            
            if(cantidadAvender>cantidadEnAlmacen){
                
                String mensaje="Solo existen "+cantidadEnAlmacen+" unidades en almacen";
                mensaje+=" del producto: \n "+productoParaCarrrito.getDescripcion()+" \n";
                mensaje+=" por lo cual no se puede vender la cantidad deseada";
                //JOptionPane.showMessageDialog(null, mensaje);
                
                if(cantidadEnAlmacen>0){
                  // codigoBusqueda.setText("");
                   //cantidadPizza.setText(""+productoParaCarrrito.getUnidadesEnCaja());
                   //codigoBusqueda.setText(productoParaCarrrito.getCodigo());
                }else{
                    //codigoBusqueda.setText("");
                    //cantidadPizza.setText("1");
                }
                
                return;
            } 
             
           //DefaultTableModel model = (DefaultTableModel) tablaDetalleVenta.getModel();
           Vector row = new Vector();

           int filasTotales=0;//model.getRowCount();
           int nuevaFila=filasTotales+1;
           row.add(nuevaFila);
           row.add("");
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
           
//           model.addRow(row);
//
//           tablaDetalleVenta.getColumn("Id producto").setWidth(0);
//           tablaDetalleVenta.getColumn("Id producto").setMinWidth(0);
//           tablaDetalleVenta.getColumn("Id producto").setMaxWidth(0);
//           tablaDetalleVenta.getColumn("Tamaño").setWidth(0);
//           tablaDetalleVenta.getColumn("Tamaño").setMinWidth(0);
//           tablaDetalleVenta.getColumn("Tamaño").setMaxWidth(0);
            ///obtenemos el descuento
           String descuentoUnidades="";//comboDescuentos.getSelectedItem().toString();
           
           String descuentoDecimal=descuentoUnidades.replace("%","");
           Double valorDescuento=0.0;
           
           if(descuentoDecimal.equals("---")){
               
           }else{
               valorDescuento=Double.parseDouble(descuentoDecimal);
               valorDescuento=valorDescuento/100;
           }
           
           double granTotal=0.0;
           for(int i=0;i<0;i++){

               Double val=0.0;//(Double)model.getValueAt(i, 3);
               String cantidad="";//(String)model.getValueAt(i, 1);
               Double valNumeric=val;
               Double cantidadNumeric=Double.parseDouble(cantidad);
               Double numericTotal=valNumeric*cantidadNumeric;
               granTotal+=numericTotal;
           }
           Double descuentoTotal=granTotal*valorDescuento;
            
//           ivaTotal.setText("$"+decimales.format(granTotal*ivaConfigurado));
//           etiquetaGranTotal.setText("$"+decimales.format((granTotal-descuentoTotal)));
//           codigoBusqueda.setText("");
//           descripcionManual.setText("");
//           cantidadPizza.setText("1");
//           codigoBusqueda.setFocusable(true);
           
           /////agregamos el producto a la lista de tipos para saber si imprimir ticket o no
           //tiposDeProductoAgregados.add(tipoDeProductoAgregado);
           
         }else{
//             JOptionPane.showMessageDialog(null,"No existe producto con el código proporcionado");
//             codigoBusqueda.setText("");
//             descripcionManual.setText("");
         }
         
       }catch(Exception e){
               e.printStackTrace();
       } 
       
   }
   private void ReimpresionTicketActionPerformed(java.awt.event.ActionEvent evt) {                                                  
       int confirmacion= 0;
      if(confirmacion==0){
      
       Utils u=new Utils();        
       //DBConect conexion=new DBConect();  
       Connection conexionMysql =null;// conexion.GetConnection();
       int venta=0;
       int cliente=0;
       int usuario=0;
       String nombreCliente="";
       String nombreUsuario="";
       
       double efectivoRecib=0.0;
       double cambio=0.0;
       ///obtenemos la Venta del consecutivo proporcionado
       String consec="";//consecutivoReimpresionTicket.getText();
       
       try{
           
           Statement statement = conexionMysql.createStatement();

           String sqlString="select * from venta where consecutivoVenta="+consec+" and "+u.obtenerBetweenParaConsulta("fechaVenta");

           ResultSet rs2=statement.executeQuery(sqlString);

           while(rs2.next()){
               venta=rs2.getInt("idVenta");
               cliente=rs2.getInt("cliente_idCliente");
               usuario=rs2.getInt("usuarios_idUsuario");
               efectivoRecib=rs2.getDouble("efectivoRecib");
               cambio=rs2.getDouble("cambio");
           }

           if(venta!=0){
               
               nombreCliente=u.obtenerNombreCompletoCliente(cliente);
               nombreUsuario=u.obtenerNombreCompletoUsuarioDelSistema(usuario);
               //JTable obtenerDetalleCompra = u.obtenerDetalleCompra(venta);
               String itemsVenta="";//obtenerProductosParaTicket(obtenerDetalleCompra);
                               
               String direccionCliente="";
               String telefonoCliente="";
               
               Statement statement2 = conexionMysql.createStatement();

               String sqlString2="select * from cliente where idCliente="+cliente;

               ResultSet rs3=statement.executeQuery(sqlString2);
               while(rs3.next()){
                   direccionCliente=rs3.getString("dirección");
                   telefonoCliente=rs3.getString("telefono");
               }
               
               if(nombreCliente.equals("Cliente en local")){
                   
                   direccionCliente="";
                   telefonoCliente="";
                   nombreCliente="";
                   do{
                       nombreCliente="";//JOptionPane.showInputDialog(null,"¿Cual es el nombre del cliente?");
//                       if(nombreCliente==null){
//                           JOptionPane.showMessageDialog(null,"Has introducido un valor vacio, por favor proporciona el nombre");
//                           nombreCliente="";
//                       }
                   }while(nombreCliente.equals(""));
               }
               
               String preTelefono=""+telefonoCliente;
                       
                       if(!preTelefono.equals("")){
                           telefonoCliente="\nTelefono del cliente:"+telefonoCliente;
                           
                           direccionCliente+=telefonoCliente;
                       }

                       ///damos el formato deseado a la hora y fecha
                       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
                       Date now = new Date();
                       String fecha=sdf.format(now);
                        
                       //DefaultTableModel model = (DefaultTableModel) obtenerDetalleCompra.getModel();
                       double granTotal=0.0;
                       
                       for(int i=0;i<0;i++){

                           Double val=0.0;//(Double)model.getValueAt(i, 3);
                           String cantidad="";//+model.getValueAt(i, 1);
                           Double valNumeric=val;
                           Double cantidadNumeric=Double.parseDouble(cantidad);
                           Double numericTotal=valNumeric*cantidadNumeric;
                           granTotal+=numericTotal;
                       }
                      
                       //ivaTotal.setText("$"+decimales.format(granTotal*ivaConfigurado));
//                       etiquetaGranTotal.setText("$"+decimales.format((granTotal)));
//                       codigoBusqueda.setText("");
//                       descripcionManual.setText("");
//                       cantidadPizza.setText("1");
//                       codigoBusqueda.setFocusable(true);
           
                       
                       Double ivaDouble=0.0;//Double.parseDouble(ivaTotal.getText().replace("$", ""));
                       Double totalDouble=0.0;//Double.parseDouble(etiquetaGranTotal.getText().replace("$", ""));
                       
                       Double subTotal=totalDouble-ivaDouble;
                     
                       boolean tipoProdVendido=true;//tiposDeProductoAgregados.contains(0);
                       if(0==1 && tipoProdVendido==true){
                          ///No imprimimos ticket, solo abrimos la caja 
                          //Ticket t=new Ticket();
                          //t.AbrirCaja();
                          //JOptionPane.showMessageDialog(null,"Abriendo caja por venta de rebanada");
                       }else{
                           ///imprimimos el ticket normalmente
                           
                           boolean tipoProd4=true;
                           
                           //obtenemos el refresco seleccionado en la interfaz
                           String refresco="";//jComboBox2.getSelectedItem().toString();
                           if(tipoProd4==true){
                               refresco="";
                           }else{                        
                               refresco="Refresco:"+refresco;
                           }
                           
                           //Ticket t=new Ticket(valorSucursal.getText(),valorDireccion.getText(),consec+"",nombreUsuario,fecha,itemsVenta,subTotal+"",ivaTotal.getText(),etiquetaGranTotal.getText(),efectivoRecib+"",cambio+"",nombreCliente,direccionCliente,refresco);
                           //imprimirLogo();
                           //t.Imprimir();

                       }
               
           }else{
               //JOptionPane.showMessageDialog(null,"No se encontro una venta con ese consecutivo");
           }    
           
           
       }catch(Exception e){
           e.printStackTrace();                  
       }
        
//       etiquetaGranTotal.setText("---");
//       efectivoRecibido.setText("");
//       cambioTotal.setText("$");
//       ivaTotal.setText("---");
//                       
//       seleccionPizza1.setSelectedIndex(0);
//       seleccionPizza2.setSelectedIndex(0);
//       seleccionPizza3.setSelectedIndex(0);
//       seleccionExtras1.setSelectedIndex(0);
//       seleccionExtras2.setSelectedIndex(0);
//       agregarUsuarioSinRegistro();
//       tiposDeProductoAgregados.clear();      
//       consecutivoReimpresionTicket.setText("");
      }
   }                                     
}
