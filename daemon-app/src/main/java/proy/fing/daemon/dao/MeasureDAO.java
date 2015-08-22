package proy.fing.daemon.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import proy.fing.daemon.model.Measure;

@Repository
public class MeasureDAO {
	
	private static final int RETRIES = 10;
	
	private static final String INSERT_QUERY = "insert into measure(value,date,mote_ip,retries) values (?,?,?,?)";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int save(Measure measure){
		
        Object[] args = new Object[] {measure.getValue(), measure.getDate(), measure.getMoteIp(), RETRIES};
        return this.jdbcTemplate.update(INSERT_QUERY, args);
	}
}
