package proy.fing.core.dao.db.generic;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS)
public class AbstractHibernateDAO {

    private static final Map<String, Object> EMPTY_PARAMS = Collections.emptyMap();
    private static final Integer EMPTY_MAX_RESULTS = null;

    @Autowired
    protected SessionFactory sessionFactory;

    protected final Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    protected int updateHQL(String hql, Object... parameters) {
        Query query = this.getCurrentSession().createQuery(hql);
        for (int i = 0; i < parameters.length; i++) {
            query.setParameter(i, parameters[i]);
        }
        return query.executeUpdate();
    }

    @SuppressWarnings("rawtypes")
    protected int updateHQL(String hql, Map<String, Object> parameters) {
        Query query = this.getCurrentSession().createQuery(hql);
        for (Entry<String, Object> param : parameters.entrySet()) {
            if (param.getValue() instanceof Collection) {
                query.setParameterList(param.getKey(), (Collection) param.getValue());
            } else {
                query.setParameter(param.getKey(), param.getValue());
            }
        }
        return query.executeUpdate();
    }

    protected int updateSQL(String sql, Object... parameters) {
        Query query = this.getCurrentSession().createSQLQuery(sql);
        for (int i = 0; i < parameters.length; i++) {
            query.setParameter(i, parameters[i]);
        }
        return query.executeUpdate();
    }

    @SuppressWarnings("rawtypes")
    protected List findByHQL(String queryString) {
        return this.findByHQL(queryString, EMPTY_PARAMS, EMPTY_MAX_RESULTS);
    }

    @SuppressWarnings("rawtypes")
    protected List findByHQL(String queryString, Object... params) {
        Query query = this.getCurrentSession().createQuery(queryString);

        for (int i = 0; i < params.length; i++) {
            query.setParameter(i, params[i]);
        }

        return query.list();
    }

    @SuppressWarnings("rawtypes")
    protected List findByHQL(final String queryString, final Map<String, Object> params) {
        return this.findByHQL(queryString, params, EMPTY_MAX_RESULTS);
    }

    @SuppressWarnings("rawtypes")
    protected List findByHQL(final String queryString, final Map<String, Object> params, Integer maxResults,
        ResultTransformer... transformers) {
        Query query = this.getCurrentSession().createQuery(queryString);

        if (!maxResults.equals(EMPTY_MAX_RESULTS)) {
            query.setMaxResults(maxResults);
        }

        for (ResultTransformer rt : transformers) {
            query.setResultTransformer(rt);
        }

        for (Entry<String, Object> param : params.entrySet()) {
            if (param.getValue() instanceof Collection) {
                query.setParameterList(param.getKey(), (Collection) param.getValue());
            } else {
                query.setParameter(param.getKey(), param.getValue());
            }
        }

        return query.list();
    }

}
