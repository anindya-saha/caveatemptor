package auction.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import auction.dao.BookDAO;
import auction.model.Book;
import auction.model.User;

public class BookHibernateDAO extends GenericHibernateDAO<Book, Long> implements BookDAO {
	
	public List<Book> search(final String title, final String author, final String[] languageNames, final Long[] categoryIds, User user, boolean onlyActive) {
		Criteria crit = getEntityManager().getSession().createCriteria(Book.class);
		crit.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		if(onlyActive) {
			crit = crit.add(Restrictions.eq("active", Boolean.valueOf(true)));
		}
		
		if((title != null) && (title.length() > 0)){
			crit = crit.add(Restrictions.like("title", title, MatchMode.ANYWHERE));
		}
		
		if((author != null) && (author.length() > 0)){
			crit = crit.add(Restrictions.like("author", author, MatchMode.ANYWHERE));
		}
		
		if((languageNames != null) && (languageNames.length > 0)){
			crit = crit.createAlias("language", "l").add(Restrictions.in("l.name", languageNames));
		}
		
		if((categoryIds != null) && (categoryIds.length > 0)) {
			crit = crit.createAlias("categories", "c").add(Restrictions.in("c.id", categoryIds));
		}
		
		// TODO add bidder
		
		return crit.list();
	}
}
