package auction.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name = "CATEGORY")
@SequenceGenerator(name = "categorysequence", sequenceName = "CATEGORY_SEQ", initialValue = 1, allocationSize = 5)
public class Category implements Serializable, Comparable<Category>  {
   
	private static final long serialVersionUID = 1L;
	
	@Id 
    @Column(name = "CATEGORY_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorysequence")
	private Long id = null;
	
    @Version
    @Column(name = "OBJ_VERSION")
    private int version = 0;
	
    @Column(name = "CATEGORY_NAME", length = 255, nullable = false)
    private String name;
    
    @Transient
    private Category parentCategory;
    
    @ManyToMany
    @JoinTable(
    	name = "CATEGORY_BOOK", 
    		joinColumns = 			@JoinColumn(name = "CATEGORY_ID"),
    		inverseJoinColumns = 	@JoinColumn(name = "BOOK_ID")
    )
    @org.hibernate.annotations.ForeignKey(
        name = "CATEGORY_BOOK_CATEGORY_ID",
        inverseName = "CATEGORY_BOOK_BOOK_ID"
    )
    private List<Book> books = new ArrayList<Book>();
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATED", nullable = false, updatable = false)
    private Date created = new Date();

    public Category(){}
    
    public Category(String name) {
		super();
		this.name = name;
	}

	public void addBook(Book book){
    	if(book == null)  throw new IllegalArgumentException("Book cannot be null");
    	this.books.add(book);
    	book.getCategories().add(this);
    }
    
    public void removeBook(Book book){
    	if(book == null)  throw new IllegalArgumentException("Book cannot be null");
    	this.books.remove(book);
    	book.getCategories().remove(this);
    }
    
    public boolean equals(Object o) {
    	if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Category category = (Category) o;

        if (this.created.getTime() != category.created.getTime()) return false;
        if (!this.name.equals(category.name)) return false;
        return this.parentCategory != null ? this.parentCategory.equals(category.parentCategory) : category.parentCategory == null;
	}

    public int hashCode() {
		int result;
        result = name.hashCode();
        result = 29 * result + (parentCategory != null ? parentCategory.hashCode() : 0);
        result = 29 * result + created.hashCode();
        return result;
	}

	public int compareTo(Category o) {
		return getName().compareTo(o.getName());
	}
	
	public String toString() {
		return new StringBuilder().append("Category ('" ).append(getId()).append("'), Name: '").append(getName()).append("'").toString();
	}
	  
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getVersion() {
		return version;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public Date getCreated() {
		return created;
	}
}
