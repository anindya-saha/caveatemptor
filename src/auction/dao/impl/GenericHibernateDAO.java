package auction.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.ejb.HibernateEntityManager;

import auction.dao.GenericDAO;

public class GenericHibernateDAO<T, ID extends Serializable> implements	GenericDAO<T, ID> {
	
	@PersistenceContext
	private HibernateEntityManager em;

	private Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public GenericHibernateDAO() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public T findById(ID id, boolean lock) {
		T entity;
		if (lock) {
			entity = getEntityManager().find(getPersistentClass(), id);
			em.lock(entity, javax.persistence.LockModeType.WRITE);
		} else {
			entity = getEntityManager().find(getPersistentClass(), id);
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getEntityManager().createQuery("from " + getPersistentClass().getName()).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByExample(T exampleInstance, String... excludeProperty) {
		// Using Hibernate, more difficult with EntityManager and EJB-QL
		Criteria crit = getEntityManager().getSession().createCriteria(getPersistentClass());
		Example example = Example.create(exampleInstance);

		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}

		crit.add(example);
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		// Using Hibernate, more difficult with EntityManager and EJB-QL
		Criteria crit = getEntityManager().getSession().createCriteria(getPersistentClass());

		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	public void persist(T entity) {
		getEntityManager().persist(entity);
	}
	
	public T merge(T entity) {
		return getEntityManager().merge(entity);
	}

	public void remove(T entity) {
		getEntityManager().remove(entity);
	}
	
	public void refresh(T entity){
		getEntityManager().refresh(entity);
	}

	public void flush() {
		getEntityManager().flush();
	}

	public void clear() {
		getEntityManager().clear();
	}

	public void setEntityManager(HibernateEntityManager em) {
		this.em = em;
	}

	protected HibernateEntityManager getEntityManager() {
		if (em == null) {
			throw new IllegalStateException("EntityManager has not been set on DAO before usage");
		}
		return em;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}
}
