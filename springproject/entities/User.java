package img3.project.springproject.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	
	@Id
	@Column(name = "user_name")
	private String name;
	
	@Column(name = "user_password")
	private String password;
	
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Expenditure> uexp;
	
	@OneToMany(mappedBy = "puser")
	@JsonIgnore
	private List<PaymentMode> paymode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Expenditure> getUexp() {
		return uexp;
	}

	public void setUexp(List<Expenditure> uexp) {
		this.uexp = uexp;
	}

	public List<PaymentMode> getPaymode() {
		return paymode;
	}

	public void setPaymode(List<PaymentMode> paymode) {
		this.paymode = paymode;
	}

}
