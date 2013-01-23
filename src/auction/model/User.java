package auction.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name="USERS")
@SequenceGenerator(name = "usersequence", sequenceName = "USER_SEQ", initialValue = 1, allocationSize = 5)
public class User implements Serializable, Comparable<User>{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersequence")
	private long id;
	
    @Version
    @Column(name = "OBJ_VERSION")
    private int version = 0;
    
	@Column(name = "USERNAME", length = 16, nullable = false, unique = true)
	private String username;
	
	@Column(name = "`PASSWORD`", length = 12, nullable = false)
	private String password;
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATED", nullable = false, updatable = false)
    private Date created = new Date();
    
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return getUsername().equals(user.getUsername());
	}

	public int hashCode() {
		return getUsername().hashCode();
	}

	public String toString() {
		return new StringBuilder().append("User ('" ).append(getId()).append("'), Username: '").append(getUsername()).append("'").toString();
	}
	  
	@Override
	public int compareTo(User o) {
		return getUsername().compareTo(o.getUsername());
	}

	public long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getCreated() {
		return created;
	}
}
