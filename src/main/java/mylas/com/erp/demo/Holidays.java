package mylas.com.erp.demo;
// Generated 27 Jun, 2018 12:58:36 PM by Hibernate Tools 5.2.8.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Holidays generated by hbm2java
 */
@Entity
@Table(name = "holidays", schema = "dbo", catalog = "mylasfyt_krishna", uniqueConstraints = {
		@UniqueConstraint(columnNames = "name"), @UniqueConstraint(columnNames = "hdate") })
public class Holidays implements java.io.Serializable {

	private Integer id;
	private String name;
	private String hdate;

	public Holidays() {
	}

	public Holidays(String name, String hdate) {
		this.name = name;
		this.hdate = hdate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", unique = true, nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "hdate", unique = true, nullable = false, length = 30)
	public String getHdate() {
		return this.hdate;
	}

	public void setHdate(String hdate) {
		this.hdate = hdate;
	}

}
