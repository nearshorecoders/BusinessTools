package com.org.pos.repository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Connection;
import com.org.pos.model.Retiro;

@Repository
public class RetiroRepository {
    
	private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("exchangeDS")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Retiro registrarRetiro(Double cantidad,String descripcion,Integer idSucursal, String usuarioLogueado){                                         
        
        Integer resultado;
        
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
        
        try{

            String getMax="select MAX(consecutivo) as max from " +
                           " retiro where (fecha BETWEEN '"+fi+"' AND '"+ff+"') AND sucursal_idsucursal="+idSucursal;
            Integer consecMax=jdbcTemplate.queryForObject(getMax, Integer.class);
            int max=1;
            if(consecMax!=null) {
         		max=consecMax+1;
         	}
             
            String sqlString2="insert into retiro(cantidad,descripcion,consecutivo,usuario,sucursal_idsucursal) VALUES("+cantidad+",'"+descripcion+"',"+max+","+usuarioLogueado+","+idSucursal+")";

            resultado=jdbcTemplate.update(sqlString2);
            Retiro retiroResult=new Retiro();
            retiroResult.setCantidad(cantidad);
            retiroResult.setConsecutivo(max);
            retiroResult.getSucursal_idsucursal();
            
            return retiroResult;
         }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }    
}
