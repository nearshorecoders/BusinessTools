package com.org.pos.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
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
