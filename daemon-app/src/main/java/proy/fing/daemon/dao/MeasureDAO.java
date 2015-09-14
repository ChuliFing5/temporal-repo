package proy.fing.daemon.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import proy.fing.daemon.model.Measure;

@Repository
public class MeasureDAO {
	
	private static final int RETRIES = 10;
	
	private static final String INSERT_QUERY = "insert into measure(value,date,mote_ip,retries) values (?,?,?,?)";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM measure";
	private static final String DELETE_ALL_QUERY = "DELETE FROM measure";
	private static final String DELETE_QUERY = DELETE_ALL_QUERY + " WHERE mote_ip=? AND date=? AND value=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	public int save(Measure measure){
		
        Object[] args = new Object[] {measure.getValue(), measure.getDate(), measure.getMoteIp(), RETRIES};
        return this.jdbcTemplate.update(INSERT_QUERY, args);
	}
	
	public List<Measure> findAll(){	
					
		List<Measure> measures  = jdbcTemplate.query(SELECT_ALL_QUERY,new BeanPropertyRowMapper(Measure.class));
			
		return measures;
	}

	public int deleteAll()
	{
		return this.jdbcTemplate.update(DELETE_ALL_QUERY);		
	}
	
	public int delete (Measure measure)
	{
		Object[] args = new Object[] {measure.getValue(), measure.getDate(), measure.getMoteIp(), RETRIES};
		return this.jdbcTemplate.update(DELETE_QUERY, args);
	}
}
