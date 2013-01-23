package auction.dao;

import java.util.List;
import java.io.Serializable;

public interface GenericDAO<T, ID extends Serializable> {

	public T findById(ID id, boolean lock);
	
	public List<T> findAll();

	public List<T> findByExample(T exampleInstance, String... excludeProperty);

	public void persist(T entity);
	
    public T merge(T entity);

    public void remove(T entity);
    
    public void refresh(T entity);
    
    public void flush();
}
