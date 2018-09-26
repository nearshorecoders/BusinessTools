package com.org.pos.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.Connection;
import com.org.pos.model.Productos;
import com.org.pos.model.Usuario;
import com.org.pos.repository.UserRepository.UsuarioRowMapper;

@Repository
public class ProductosRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("exchangeDS")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Transactional(readOnly=true)
    public Usuario findByUsername(String usuario) {
    	Usuario us = null;
    	try {
    		us = jdbcTemplate.queryForObject("SELECT * FROM usuarios WHERE email = ?", 
        			new Object[]{usuario}, new UsuarioRowMapper());    		
    	} catch(IncorrectResultSizeDataAccessException e) {
    		LOGGER.error("Invalid user");
    	}

    	return us;
    }
    
    
    /**
     * Obtiene lasumatoria de los dolares en depositos de banco que no estan bloqueados, validados o cancelados
     */
    @Transactional(readOnly=true)
    public Map<String, String> getBankFlow() throws Exception{
        try {
        	String query = "SELECT sum(dep.dolares) as total " + 
        			" FROM public.deposito as dep ";
        	
        	//List<Map<String, Object>> list = 
        	BigDecimal sumatoria=jdbcTemplate.queryForObject(query, BigDecimal.class);
        	Map<String, String> map = new HashMap<String, String>();
        	map.put("totalBalanceDlls", sumatoria.toString());
        	
        	return map;
        }catch (Exception e){
        	LOGGER.error("Error", e);
            throw e;
        }
    }
    
    /**
     * Inserta un registro
     * @param idDepo
     * @param status
     * @param idUser
     * @return
     * @throws Exception
     */
    public Integer insertHistoricDeposit(Long idDepo, Integer status, Long idUser) throws Exception{
        try {
        	String query = "INSERT INTO public.deposito_historico(id_deposito, status, id_usuario, fecha) "+
    		"VALUES (?, ?, ?, ?);";
        	
        	Integer result = jdbcTemplate.update(query, new Object[] {idDepo, status, idUser, new Timestamp(new Date().getTime())});
        	
        	return result;
        }catch (Exception e){
        	LOGGER.error("Error", e);
            throw e;
        }
    }
    
    /**
     * Actualizacion del deposito
     * @param idDepo
     * @param status
     * @param observaciones
     * @param folioAuditoria
     * @param grillaBnc
     * @return
     * @throws Exception
     */
    public Integer updateDeposit(Long idDepo, Integer status, String observaciones, String folioAuditoria, String grillaBnc) throws Exception{
        try {
        	String query = "UPDATE public.deposito\n" + 
        			"	SET status=?, grilla_bancaria=?, observaciones=?, folio_auditoria=?\n" + 
        			"	WHERE id = ?;";
        	
        	Integer result = jdbcTemplate.update(query, new Object[] {status, grillaBnc, observaciones, folioAuditoria, idDepo});
        	
        	return result;
        }catch (Exception e){
        	LOGGER.error("Error", e);
            throw e;
        }
    }
    
    public void marcarProductoComoUtilizadoEnVenta(String idProd,String cantidadAlmacen,String cantidadVendida, String catidadVendidaAlMomento){
        ///se descuenta del total de unidades y se incrementa el numero de vendidos
        
        Double cantidadAl=Double.parseDouble(cantidadAlmacen);
        Double cantidadVe=Double.parseDouble(cantidadVendida);
        Double cantidadVendidaAlMomento=Double.parseDouble(catidadVendidaAlMomento);
        Integer resultado=0;
        try{
        	
          String sqlString="Update productos set unidadesEnCaja="+ (cantidadAl-cantidadVendidaAlMomento)+
                           " , cantidadVendidos="+(cantidadVe+cantidadVendidaAlMomento)+
                           " where idProductos='"+idProd+"'";
          
          resultado=jdbcTemplate.update(sqlString);

        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public Integer agregarProductoABD(Productos producto) {                                                
    	Integer resultado=0;
        String tipoProdSel="0";
        try{
            String varDescripcion=producto.getDescripcion();
            Double varUnidades=producto.getUnidadesEnCaja();
            Double varPrecioC=producto.getPrecioCompra();
            Double varPrecioV=producto.getPrecioVenta();
            String varUMedida=producto.getUnidadMedida();
            String varPresentacion=producto.getPresentacion();
            String varCodigo=producto.getCodigo();
            
            String precioChica="";//pChica.getText();
            String precioMediana="";//pMediana.getText();
            String precioGrande="";//pGrande.getText();
            String precioFamiliar="";//pFamiliar.getText();
            
            String tipoProd="0";
            //0 general
            //1 pizza
            
            if(tipoProdSel.equals("---")){
                tipoProd="0";
            }
            if(tipoProdSel.equals("Pizza")){
                tipoProd="1";
                varPrecioC=0.0;
                varPrecioV=0.0;
                
            }
            if(tipoProdSel.equals("Tacos")){
                tipoProd="2";
            }
            if(tipoProdSel.equals("General")){
                tipoProd="0";
                precioChica="0";
                precioMediana="0";
                precioGrande="0";
                precioFamiliar="0";
            }
            
            String sqlString="INSERT INTO `productos` (`descripcion`, `unidadesEnCaja`, `precioUnitarioC`, `uMedida`, `presentacion`, `cantidadFraccion`, `codigo`,`precioUnitarioV`,`TipoProducto`,precioChica,precioMediana,precioGrande,precioFamiliar) "
                    + " VALUES ('"+varDescripcion+"', '"+varUnidades+"', '"+varPrecioC+"', '"+varUMedida+"', '"+varPresentacion+"', '0', '"+varCodigo+"', '"+varPrecioV+"',"+tipoProd+","+precioChica+","+precioMediana+","+precioGrande+","+precioFamiliar+")";
        
            resultado=jdbcTemplate.update(sqlString);
            
       }catch(Exception e){
           e.printStackTrace();
       }
        
        return resultado;
        
    }   
    private void busquedaProductos(String descripcion) {                                                      
        try{

          String descripcionVar=descripcion;
          
          String sqlString="Select idProductos as 'Id',codigo,descripcion as 'Descripción',precioUnitarioC as 'Precio compra', precioUnitarioV as 'Precio venta',unidadesEnCaja as 'Cantidad'"
                  + ",uMedida as 'Unidad medida', presentacion as 'Presentación' from  productos " +
                           " where estatus=0 ";
              
               	sqlString+= " And descripcion like '%"+descripcionVar+"%'";

          
            //ResultSet rs = jdbcTemplate.query(sqlString); 
            //int contador=0;  
     
            //ResultSetMetaData rsMd = rs.getMetaData();
            //La cantidad de columnas que tiene la consulta
            //int cantidadColumnas = rsMd.getColumnCount();
            //Establecer como cabezeras el nombre de las colimnas
//            for (int i = 1; i <= cantidadColumnas; i++) {
//             //productosEncontradosAModificar.addColumn(rsMd.getColumnLabel(i));
//            }
//            //Creando las filas para el JTable
//            while (rs.next()) {
//             contador++;
//             Object[] fila = new Object[cantidadColumnas];
//             for (int i = 0; i < cantidadColumnas; i++) {
//               fila[i]=rs.getObject(i+1);
//             }
//             //productosEncontradosAModificar.addRow(fila);
//            }
//          
//            if(contador==0){
//                //JOptionPane.showMessageDialog(null, "No se encontro ningun producto con los datos proporcionados.");
//            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    } 
    private Integer modificarProductos(Productos producto) {                                                          

        String idProductoModificar=producto.getId();
        String codigo=producto.getCodigo();
        Integer resultado=0;
        try{

          String descripcionVar=producto.getDescripcion();
          Double precioCVar=producto.getPrecioCompra();
          Double precioVVar=producto.getPrecioVenta();
          Double cantidad=producto.getUnidadesEnCaja();
          String uM=producto.getUnidadMedida();
          String presentacion=producto.getPresentacion();

          String sqlString="UPDATE productos set codigo='"+codigo+"' descripcion='"+descripcionVar+"',precioUnitarioC="+precioCVar+", "
                            + " precioUnitarioV="+precioVVar+",unidadesEnCaja="+cantidad+",uMedida='"+uM+"', presentacion='"+presentacion+"'"
                            +" where idProductos='"+idProductoModificar+"'";
          
          resultado=jdbcTemplate.update(sqlString);

        }catch(Exception e){
            e.printStackTrace();
        }
        
        return resultado;
    }
    public Integer removerProducto(String idProducto) {                                                

            String idProductoModificar=idProducto;
            Integer resultado=0;
            try{

              String sqlString="UPDATE productos set activo=0"
                                +" where idProductos='"+idProductoModificar+"'";

              resultado=jdbcTemplate.update(sqlString);

            }catch(Exception e){
                e.printStackTrace();
            }
       
        return resultado;
    }                                             
	private void busquedaPorDescripcion(java.awt.event.ActionEvent evt) {                                               
	        
	        //DBConect conexion=new DBConect();
	        try{
	
	          //Connection conexionMysql = conexion.GetConnection();
	
	          Statement statement = null;//conexionMysql.createStatement();
	
	          String codigoVar="";//codigoManual.getText();
	          String descripcionVar="";//descripcionManual.getText();
	          
	          String filter=codigoVar+descripcionVar;
	        
	          if(filter.equals("")){
	            //JOptionPane.showMessageDialog(null, "Se necesita al menos un dato para realizar la busqueda de productos");
	            return;
	          }
	          
	          String sqlString="Select idProductos as 'Id',codigo,descripcion as 'Descripción',precioUnitarioC as 'Precio compra', precioUnitarioV as 'Precio venta',unidadesEnCaja as 'Cantidad'"
	                  + ",uMedida as 'Unidad medida', presentacion as 'Presentación' from  productos " +
	                           " where estatus=0 and codigo='"+codigoVar+"'";
	                           
	          if(!descripcionVar.equals("")){
	               sqlString+= " And descripcion like '%"+descripcionVar+"%'";
	          }
	          
	          if(codigoVar.equals("") && !descripcionVar.equals("")){
	              
	              sqlString="SELECT idProductos as 'Id',descripcion as 'Descripción',precioUnitarioV as 'Precio venta',unidadesEnCaja as 'Cantidad' "
	                      +",uMedida as 'Unidad medida', presentacion as 'Presentación'"
	                      + " FROM productos where estatus=0 and descripcion like '%"+descripcionVar+"%'";
	              
	          }
	          
	            ResultSet rs = statement.executeQuery(sqlString); 
	            int contador=0;  
	            
	            while (rs.next()) {
	             contador++;
	            }
	            rs.beforeFirst();
	            if(contador>=1){
	                
	               
	                //DefaultTableModel productosEncontrados= new DefaultTableModel();
	                
	                //ResultSetMetaData rsMd = rs.getMetaData();
	                //La cantidad de columnas que tiene la consulta
	                //int cantidadColumnas = rsMd.getColumnCount();
	                //Establecer como cabezeras el nombre de las colimnas
	                //for (int i = 1; i <= cantidadColumnas; i++) {
	                 //productosEncontrados.addColumn(rsMd.getColumnLabel(i));
	                //}
	                //Creando las filas para el JTable
	                //while (rs.next()) {
	                 //Object[] fila = new Object[cantidadColumnas];
	                 //for (int i = 0; i < cantidadColumnas; i++) {
	                 //  fila[i]=rs.getObject(i+1);
	                 //}
	                 //productosEncontrados.addRow(fila);
	                //}
	                
	                
	                //BuscadorProductos buscador=new BuscadorProductos(productosEncontrados,this);
	                //buscador.setTitle("Buscador de productos");
	                //buscador.setVisible(true);
	                //buscador.toFront();
	                
	            }else{
	                
	            	//JOptionPane.showMessageDialog(null,"No se encontraron resultados en búsqueda manual");
	                return;
	            }
	            
	        }catch(Exception e){
	            e.printStackTrace();
	        } 
	    }                                          
	
    class UsuarioRowMapper implements RowMapper<Usuario> {
        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Usuario usuario = new Usuario();

        	usuario.setId(rs.getInt("idusuario"));
        	usuario.setNombre(rs.getString("nombre"));
        	usuario.setApellidop(rs.getString("apellidop"));
        	usuario.setApellidom(rs.getString("apellidom"));
        	usuario.setNombreUsuario(rs.getString("nombreUsuario"));
        	usuario.setPassword(rs.getString("password"));
        	usuario.setIdRol(rs.getInt("rol_idrol"));
        	//usuario.setCorreo(rs.getString("correo"));
        	//usuario.setTelefono(rs.getString("telefono"));
        	//usuario.setId_rol(rs.getInt("id_rol"));
        	//usuario.setId_supervisor(rs.getInt("id_supervisor"));
            return usuario;
        }
        
    }
	
}
