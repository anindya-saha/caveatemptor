package auction.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name="BOOK")
@SequenceGenerator(name = "booksequence", sequenceName = "BOOK_SEQ", initialValue = 1, allocationSize = 5)
public class Book implements Serializable, Comparable<Book> {
    
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "BOOK_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booksequence")
    private Long id = null;
	
    @Version
    @Column(name = "OBJ_VERSION")
    private int version = 0;
	
	@Column(name = "TITLE", length = 255, nullable = false, updatable = false)
    private String title;

	@Column(name = "AUTHOR", length = 255, nullable = false, updatable = true)
    private String author;
	
	@Column(name = "DESCRIPTION", length = 4000)
    private String description;
	
    @ManyToMany(mappedBy = "books")
    private Set<Category> categories = new HashSet<Category>();
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATED", nullable = false, updatable = false)
    private Date created = new Date();
    
    public Book() {}
    
    public Book(String title, String author) {
		super();
		this.title = title;
		this.author = author;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Book)) return false;
		Book user = (Book) o;
		return getTitle().equals(user.getTitle());
	}

	public int hashCode() {
		return getTitle().hashCode();
	}

	public String toString() {
		return new StringBuilder().append("Book ('" ).append(getId()).append("'), Title: '").append(getTitle()).append("'").toString();
	}
	  
	@Override
	public int compareTo(Book o) {
		return getTitle().compareTo(o.getTitle());
	}

	public Long getId() {
		return id;
	}
	
	public int getVersion() {
		return version;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
	public Date getCreated() {
		return created;
	}
	
}
