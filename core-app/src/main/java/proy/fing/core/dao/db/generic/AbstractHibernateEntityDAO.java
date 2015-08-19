package proy.fing.core.dao.db.generic;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import proy.fing.core.dao.db.AbstractEntity;

/**
 * Provides a way to use annotations to load up DAOs.
 * 
 * @param <T> the type of entity.
 * @param <ID> identifier of an entity.
 */
public abstract class AbstractHibernateEntityDAO<T extends AbstractEntity, ID extends Serializable>
    extends AbstractHibernateDAO {

    // Limit para no romper todo
    private static final int MAX_RESULTS = 2000;

    protected Class<T> clazz;

    @PostConstruct
    @SuppressWarnings("unchecked")
    protected void loadGenericClass() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = ((Class<T>) parameterizedType.getActualTypeArguments()[0]);
    }

    protected Class<T> getClazz() {
        return this.clazz;
    }

    protected DetachedCriteria createCriteria(String classAlias) {
        return DetachedCriteria.forClass(this.clazz, classAlias == null ? this.clazz.getSimpleName().toLowerCase()
            : classAlias);
    }

    @SuppressWarnings("unchecked")
    public T read(ID id) {
        return (T) this.getCurrentSession().get(this.clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> readAll() {
        return this.getSession().setMaxResults(MAX_RESULTS).list();
    }

    public T load(ID id) {
        T entity = this.read(id);
        if (entity == null) {
            throw new javax.persistence.EntityNotFoundException("Unable to find " + this.clazz.getSimpleName()
                + " with id #" + id);
        }
        return entity;
    }

    @SuppressWarnings("unchecked")
    protected List<T> findByProperty(String property, Object value) {
        return this.getSession().add(Restrictions.eq(property, value)).setMaxResults(MAX_RESULTS).list();
    }

    @SuppressWarnings("unchecked")
    protected List<T> findByProperties(Map<String, Object> properties) {
        Criteria criteria = this.getSession();
        for (Entry<String, Object> property : properties.entrySet()) {
            criteria = criteria.add(Restrictions.eq(property.getKey(), property.getValue()));
        }
        return criteria.setMaxResults(MAX_RESULTS).list();
    }

    @SuppressWarnings("unchecked")
    protected List<T> findByRestrictionProperties(List<SimpleExpression> expressions) {
        Criteria criteria = this.getSession();
        this.addRestrictions(criteria, expressions);
        return criteria.setMaxResults(MAX_RESULTS).list();
    }

    protected void addRestrictions(Criteria criteria, List<SimpleExpression> expressions) {
        for (SimpleExpression simpleExpression : expressions) {
            criteria = criteria.add(simpleExpression);
        }
    }

    @SuppressWarnings("deprecation")
    protected Criteria join(String associationPath, String alias, int joinType) {
        Criteria criteria = this.getSession();
        criteria.createAlias(associationPath, alias, joinType);
        return criteria;
    }

    protected Criteria getSession() {
        return this.getCurrentSession().createCriteria(this.clazz);
    }

    protected T readByProperty(String property, Object value) {
        List<T> result = this.findByProperty(property, value);
        return result.isEmpty() ? null : result.get(0);
    }

    protected T loadByProperty(String property, Object value) {
        List<T> result = this.findByProperty(property, value);
        this.validateUnique(result);
        if (result.isEmpty()) {
            throw new javax.persistence.EntityNotFoundException();
        }
        return result.get(0);
    }

    protected T readByProperties(Map<String, Object> properties) {
        List<T> result = this.findByProperties(properties);
        this.validateUnique(result);
        return result.isEmpty() ? null : result.get(0);
    }

    protected T loadByProperties(Map<String, Object> properties) {
        List<T> result = this.findByProperties(properties);
        if (result.isEmpty()) {
            StringBuilder sb = new StringBuilder("Unable to find ");
            sb.append(this.clazz.getSimpleName()).append("with properties: ");
            for (Entry<String, Object> property : properties.entrySet()) {
                sb.append(" - ").append(property.getKey()).append(": ").append(properties.get(property.getValue()));
            }
            throw new javax.persistence.EntityNotFoundException(sb.toString());
        }
        this.validateUnique(result);
        return result.get(0);
    }


    // Validations

    protected void validateUnique(List<T> result) {
        if (result.size() > 1) {
            throw new javax.persistence.NonUniqueResultException("Expected single result, but got " + result);
        }
    }

    protected void validateOnly(List<T> result) {
        if (result.size() != 1) {
            throw new javax.persistence.NonUniqueResultException("Expected one result, but got " + result);
        }
    }

    // @SuppressWarnings("rawtypes")
    // public List findBySQL(String queryString, Object... parameters) {
    // SQLQuery query = this.getCurrentSession().createSQLQuery(queryString);
    // for (int i = 0; i < parameters.length; i++) {
    // query.setParameter(i, parameters[i]);
    // }
    // return query.list();
    // }

    // protected int updateSQL(String sql, T prototypeEntity) {
    // Query query = this.getCurrentSession().createSQLQuery(sql);
    // query.setProperties(prototypeEntity);
    // return query.executeUpdate();
    // }

    public void save(T entity) {
        this.getCurrentSession().saveOrUpdate(entity);
    }

    public void update(T entity) {
        if (entity.getIdentifier() == null) {
            throw new javax.persistence.PersistenceException("Update entity " + entity.getClass() + " without id");
        }
        this.getCurrentSession().update(entity);
    }

    public void refresh(T entity) {
        this.getCurrentSession().refresh(entity);
    }

    public void flush() {
        this.getCurrentSession().flush();
    }

    public void delete(T entity) {
        this.getCurrentSession().delete(entity);
    }

    public void delete(ID id) {
        T entity = this.load(id);
        this.getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<T> find(int firstResult, Integer maxResult) {
        Criteria criteria = this.getSession();
        criteria.setMaxResults(maxResult);
        criteria.setFirstResult(firstResult);
        return criteria.list();
    }

    public void paginateSearch(Criteria criteria, Integer resultsPerPage, Integer pageNumber) {
        if (pageNumber != null && resultsPerPage != null && criteria != null) {
            criteria.setMaxResults(resultsPerPage);
            criteria.setFirstResult(this.getFirstResult(pageNumber, resultsPerPage));
        }
    }

    private int getFirstResult(int pageNumber, int resultsPerPage) {
        if (pageNumber > -1 && resultsPerPage > 0) {
            return pageNumber * resultsPerPage;
        } else {
            return 0;
        }
    }

}
