package com.org.pos.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

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

import com.org.pos.model.Sucursal;
import com.org.pos.model.Usuario;
import com.org.pos.repository.UserRepository.UsuarioRowMapper;

@Repository
public class SucursalRepository {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("exchangeDS")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Transactional(readOnly=true)
    public Sucursal getSucursalByLoguedUser(String usuario) {
    	Sucursal us = null;
    	try {
    		us = jdbcTemplate.queryForObject("SELECT * FROM sucursal a INNER JOIN usuarios b ON a.idsucursal=b.sucursal_idsucursal WHERE b.idusuario = ?", 
        			new Object[]{usuario}, new SucursalRowMapper());    		
    	} catch(IncorrectResultSizeDataAccessException e) {
    		LOGGER.error("Invalid sucursal");
    	}

    	return us;
    }
	
    class SucursalRowMapper implements RowMapper<Sucursal> {
        @Override
        public Sucursal mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Sucursal sucursal = new Sucursal();

        	sucursal.setIdsucursal(rs.getInt("idsucursal"));
        	sucursal.setDireccion(rs.getString("direccion"));
        	sucursal.setNombre(rs.getString("nombre"));
        	sucursal.setTelefono(rs.getString("telefono"));

            return sucursal;
        }
        
    }
    
}
