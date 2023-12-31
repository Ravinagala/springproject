package img3.project.springproject.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
	@Id
	@Column(name = "cat_code")
	private String catcode;
	
	@Column(name = "cat_name")
	private String catname;
	
	@OneToMany(mappedBy = "cats")
	@JsonIgnore
	private List<Expenditure> cexp;

	public String getCatcode() {
		return catcode;
	}

	public void setCatcode(String catcode) {
		this.catcode = catcode;
	}

	public String getCatname() {
		return catname;
	}

	public void setCatname(String catname) {
		this.catname = catname;
	}

	public List<Expenditure> getCexp() {
		return cexp;
	}

	public void setCexp(List<Expenditure> cexp) {
		this.cexp = cexp;
	}

}


