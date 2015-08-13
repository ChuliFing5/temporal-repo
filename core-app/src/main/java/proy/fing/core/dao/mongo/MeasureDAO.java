package proy.fing.core.dao.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import proy.fing.core.model.Measure;

@Repository
public interface MeasureDAO extends MongoRepository<Measure, Long>{
	
	
	
}
