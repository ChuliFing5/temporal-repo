package proy.fing.core.dao;

import org.springframework.stereotype.Repository;

import proy.fing.core.dao.db.generic.AbstractHibernateEntityDAO;
import proy.fing.core.model.Daemon;
import proy.fing.core.model.Network;

@Repository
public class NetworkDAO extends AbstractHibernateEntityDAO<Network,String> { 

}
