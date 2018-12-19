package com.org.pos.repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.Connection;
import com.org.pos.model.Usuario;

//import com.cis.exchange.domain.Usuario;

@Repository
public class UserRepository {

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
    
    @Transactional(readOnly=true)
    public Usuario findByUser(String usuario) {
    	Usuario us = null;
    	try {
    		us = jdbcTemplate.queryForObject("SELECT * FROM usuarios WHERE nombreUsuario = ?", 
        			new Object[]{usuario}, new UsuarioRowMapper());    		
    	} catch(IncorrectResultSizeDataAccessException e) {
    		LOGGER.error("Invalid user");
    	}

    	return us;
    }
    private void AgregarNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        //DBConect conexion=new DBConect();
          
          try{
              Connection conexionMysql = null;//conexion.GetConnection();

              Statement statement = conexionMysql.createStatement();

              String varUsuario="";//newUserToAdd.getText();
              String varPass="";//newPassToAdd.getText();
              String varNombre="";//newNameToAdd.getText();
                          
              String validadorVacios=varUsuario+varPass+varNombre;
              
              if(validadorVacios.equals("")){
                  
                  //JOptionPane.showMessageDialog(null,"No has proporcionado ningun dato");
                  
              }
              
              String mensajeError="";
              
              
              if(varUsuario.equals("")){
                  
                  mensajeError+="Usuario \n";
                  
              }
              
              if(varPass.equals("")){
                  
                  mensajeError+="Contraseña \n";
                  
              }
              
               if(varNombre.equals("")){
                  
                  mensajeError+="Nombre \n";
                  
              }
              
              if(!mensajeError.equals("")){
              
                  mensajeError="Los siguientes campos son necesarios para continuar: \n\n "+mensajeError;
                  
                  //JOptionPane.showMessageDialog(null, mensajeError);
                  return; 
              }
               
              ///procesamos los checkbox
              int varPermisoClientes=0;
              int varPermisoProductos=0;
              int varPermisoVentas=0;
              int varPermisoUsuarios=0;
              int varPermisoReportes=0;
              
//              if(c1.isSelected()){
//                  varPermisoClientes=1;
//              }
//              if(c2.isSelected()){
//                  varPermisoProductos=1;
//              }
//              if(c3.isSelected()){
//                  varPermisoVentas=1;
//              }
//              if(c4.isSelected()){
//                  varPermisoUsuarios=1;
//              }
//              if(c5.isSelected()){
//                  varPermisoReportes=1;
//              }
                
              String sqlString="INSERT INTO `usuarios` (nombreUsuario,password,NombreCompleto,c1,c2,c3,c4,c5) "
                      + " VALUES ('"+varUsuario+"','"+varPass+"','"+varNombre+"',"+varPermisoClientes+","+varPermisoProductos+","+varPermisoVentas+","+varPermisoUsuarios+","+varPermisoReportes+")";
          
              int resultado=statement.executeUpdate(sqlString);
         
              if(resultado>0){

//                  JOptionPane.showMessageDialog(null, "El usuario se agrego correctamente");
//                  newUserToAdd.setText("");
//                  newPassToAdd.setText("");
//                  newNameToAdd.setText("");
              }
              
         }catch(Exception e){
             e.printStackTrace();
         }
      }
    
    private void DesactivarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        int fila=0;//listadoUsuariosRegistrados.getSelectedRowCount();
        if(fila==0){
            //JOptionPane.showMessageDialog(null, "No se ha seleccionado un usuario para desactivar");
            return;
        }else{
            fila=0;//listadoUsuariosRegistrados.getSelectedRow();
        }
        
        int confirmacion= 0;
        if(confirmacion==0){
            String idUsuario="";//+listadoUsuariosRegistrados.getValueAt(fila, 9);
        
             //DBConect conexion=new DBConect();  

             try{

               Connection conexionMysql = null;//conexion.GetConnection();

               Statement statement = conexionMysql.createStatement();

               String sqlString="Update usuarios set estatus=0 "
                                + " where idUsuario="+idUsuario;

               int resultado=statement.executeUpdate(sqlString);
               if(resultado>=1){
                   //JOptionPane.showMessageDialog(null, "Se desactivo el usuario correctamente.");
                   //BuscarUusariosRegistradosActionPerformed(evt);
               }else{
                   //JOptionPane.showMessageDialog(null, "Ocurrio un error al desactivar el usuario, por favor intente nuevamente.");
               }

             }catch(Exception e){
                 e.printStackTrace();
             }
        }
        
//         newUserToAdd1.setText("");
//         newPassToAdd2.setText("");
//         newNameToAdd2.setText("");
//         panelModificarUsuario.setVisible(false);
     }                                                 
    private void BuscarUsuariosRegistradosActionPerformed(java.awt.event.ActionEvent evt) {                                                          
        //DBConect conexion=new DBConect();  
        
        try{

          //usuariosEncontrados= new DefaultTableModel();
          //listadoUsuariosRegistrados.setModel(usuariosEncontrados);
          Connection conexionMysql = null;//conexion.GetConnection();

          Statement statement = conexionMysql.createStatement();
          String varUsuario="";//usuarioFiltro.getText();
          String varNombre="";//nombreUsuarioFiltro.getText();
         

          String varBusqueda=varUsuario+varNombre;

          if(varBusqueda.equals("")){
              //JOptionPane.showMessageDialog(null,"Se necesita al menos un campo para realizar la busqueda"); 
              return;
          }

          String sqlString="Select nombreUsuario as 'Usuario',password as 'Contraseña', NombreCompleto as 'Nombre completo', estatus as 'Activo'" +
                           ",c1,c2,c3,c4,c5,idUsuario from usuarios where ";

          int indicadorAnd=0;

          if(!varUsuario.equals("")){
              sqlString+=" nombreUsuario like '"+varUsuario+"%'";
              indicadorAnd++;
          }

          if(!varNombre.equals("")){
              if(indicadorAnd>0){
                  sqlString+=" and ";
              } 
              sqlString+=" NombreCompleto like '"+varNombre+"%'";
               indicadorAnd++;
          }

          ResultSet rs = statement.executeQuery(sqlString);

          ResultSetMetaData rsMd = rs.getMetaData();
          //La cantidad de columnas que tiene la consulta
          int cantidadColumnas = rsMd.getColumnCount();
          //Establecer como cabezeras el nombre de las colimnas
          for (int i = 1; i <= cantidadColumnas; i++) {
           //usuariosEncontrados.addColumn(rsMd.getColumnLabel(i));
          }
          //Creando las filas para el JTable
          int resultados=0;
          while (rs.next()) {
           resultados++;
              Object[] fila = new Object[cantidadColumnas];
           for (int i = 0; i < cantidadColumnas; i++) {
             fila[i]=rs.getObject(i+1);
           }
           //usuariosEncontrados.addRow(fila);
          }
          
          if(resultados==0){
              //JOptionPane.showMessageDialog(null,"No se encontro un usuario con los datos proporcionados");
          }
          
//          listadoUsuariosRegistrados.getColumn("c1").setWidth(0);
//          listadoUsuariosRegistrados.getColumn("c1").setMinWidth(0);
//          listadoUsuariosRegistrados.getColumn("c1").setMaxWidth(0);
//          
//          listadoUsuariosRegistrados.getColumn("c2").setWidth(0);
//          listadoUsuariosRegistrados.getColumn("c2").setMinWidth(0);
//          listadoUsuariosRegistrados.getColumn("c2").setMaxWidth(0);
//          
//          listadoUsuariosRegistrados.getColumn("c3").setWidth(0);
//          listadoUsuariosRegistrados.getColumn("c3").setMinWidth(0);
//          listadoUsuariosRegistrados.getColumn("c3").setMaxWidth(0);
//          
//          listadoUsuariosRegistrados.getColumn("c4").setWidth(0);
//          listadoUsuariosRegistrados.getColumn("c4").setMinWidth(0);
//          listadoUsuariosRegistrados.getColumn("c4").setMaxWidth(0);
//          
//          listadoUsuariosRegistrados.getColumn("c5").setWidth(0);
//          listadoUsuariosRegistrados.getColumn("c5").setMinWidth(0);
//          listadoUsuariosRegistrados.getColumn("c5").setMaxWidth(0);
//          
//          listadoUsuariosRegistrados.getColumn("idUsuario").setWidth(0);
//          listadoUsuariosRegistrados.getColumn("idUsuario").setMinWidth(0);
//          listadoUsuariosRegistrados.getColumn("idUsuario").setMaxWidth(0);
          
        }catch(Exception e){
               e.printStackTrace();
        }
    }    
    
private void actualizarPermisosUsuario(java.awt.event.ActionEvent evt) {                                         
        
        String idUsuario="";//+listadoUsuariosRegistrados.getValueAt(listadoUsuariosRegistrados.getSelectedRow(), 9);
        
        String usuario="";//newUserToAdd1.getText();
        String pass="";//newPassToAdd2.getText();
        String nombre="";//newNameToAdd2.getText();
        String permiso1="",permiso2="",permiso3="",permiso4="",permiso5="";
        
//        if(c12.isSelected()){
//            permiso1="1";
//        }else{
//             permiso1="0";
//        }
//        
//        if(c22.isSelected()){
//            permiso2="1";
//        }else{
//             permiso2="0";
//        }
//        
//        if(c32.isSelected()){
//            permiso3="1";
//        }else{
//             permiso3="0";
//        }
//        
//        if(c42.isSelected()){
//            permiso4="1";
//        }else{
//             permiso4="0";
//        }
//        
//        if(c52.isSelected()){
//            permiso5="1";
//        }else{
//             permiso5="0";
//        }
        
        //DBConect conexion=new DBConect();  
        
        try{

          Connection conexionMysql = null;//conexion.GetConnection();

          Statement statement = conexionMysql.createStatement();
          
          String sqlString="Update usuarios set nombreUsuario='"+ usuario+
                           "', password='"+pass+
                           "', NombreCompleto='"+nombre+"',c1="+permiso1+",c2="+permiso2+",c3="+permiso3+",c4="+permiso4+",c5="+permiso5
                           + " where idUsuario="+idUsuario;
          
          int resultado=statement.executeUpdate(sqlString);
          if(resultado>=1){
              //JOptionPane.showMessageDialog(null, "Se modificaron los datos de usuario correctamente.");
              //BuscarUusariosRegistradosActionPerformed(evt);
          }else{
              //JOptionPane.showMessageDialog(null, "Ocurrio un error al guardar los cambios, por favor intente nuevamente.");
          }
          
        }catch(Exception e){
            e.printStackTrace();
        }
        
//        newUserToAdd1.setText("");
//        newPassToAdd2.setText("");
//        newNameToAdd2.setText("");
//        panelModificarUsuario.setVisible(false);
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
        	usuario.setEmail(rs.getString("email"));
        	usuario.setIdRol(rs.getInt("rol_idrol"));
        	usuario.setSucursal_idsucursal(rs.getInt("sucursal_idsucursal"));
            return usuario;
        }
        
    }
}
