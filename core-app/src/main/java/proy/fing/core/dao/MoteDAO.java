package proy.fing.core.dao;

import org.springframework.stereotype.Repository;

import proy.fing.core.dao.db.generic.AbstractHibernateEntityDAO;
import proy.fing.core.model.Mote;

@Repository
public class MoteDAO extends AbstractHibernateEntityDAO<Mote,String> {

}
