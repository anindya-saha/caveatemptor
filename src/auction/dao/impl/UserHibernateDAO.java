package auction.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import auction.dao.UserDAO;
import auction.model.User;

public class UserHibernateDAO extends GenericHibernateDAO<User, Long> implements UserDAO {

	public boolean validateLogin(User user) {
		// Using Hibernate, more difficult with EntityManager and EJB-QL
		Criteria crit = getEntityManager().getSession().createCriteria(getPersistentClass());
		crit.add(Restrictions.eq("username", user.getUsername()));
		crit.add(Restrictions.eq("password", user.getPassword()));
		
		return crit.uniqueResult() != null ? true : false ;
	}

}
