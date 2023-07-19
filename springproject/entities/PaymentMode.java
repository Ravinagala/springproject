package img3.project.springproject.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class PaymentMode {
	
	@Id
	@Column(name = "payment_code")
	private int paycode;
	
	@Column(name = "payment_name")
	private String paymentname;

	
	@Column(name = "remarks")
	private String paymentremarks;
	
	@Column(name="user_name")
	private String username;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_name", updatable = false, insertable = false)
	private User puser;
	
	@JsonIgnore
	@OneToMany(mappedBy = "paymode")
	private List<Expenditure> pexp;

	public int getPaycode() {
		return paycode;
	}

	public void setPaycode(int paycode) {
		this.paycode = paycode;
	}

	public String getPaymentname() {
		return paymentname;
	}

	public void setPaymentname(String paymentname) {
		this.paymentname = paymentname;
	}

	public String getPaymentremarks() {
		return paymentremarks;
	}

	public void setPaymentremarks(String paymentremarks) {
		this.paymentremarks = paymentremarks;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User getPuser() {
		return puser;
	}

	public void setPuser(User puser) {
		this.puser = puser;
	}

	public List<Expenditure> getPexp() {
		return pexp;
	}

	public void setPexp(List<Expenditure> pexp) {
		this.pexp = pexp;
	}

		
}

