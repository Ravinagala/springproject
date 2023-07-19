package img3.project.springproject.entities;


import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "expenditures")
public class Expenditure {
	
	@Id
	@Column(name = "exp_id")
	private int expid;
	
	@Column(name = "user_name")
	private String username;
	
	@Column(name = "cat_code")
	private String catcode;
	
	@Column(name = "payment_code")
	private int paycode;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "spent_on")
	private Date date;
	
	@Column(name = "exp_description")
	private String expdesc;
	
	
	@Column(name = "exp_remarks")
	private String remarks;
	
	@Column(name = "tags")
	private String exptags;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name ="user_name", referencedColumnName = "user_name", updatable = false, insertable = false)
	private User user;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "cat_code", referencedColumnName = "cat_code", updatable = false, insertable = false)
	private Category cats;
    
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "payment_code", referencedColumnName = "payment_code", updatable = false, insertable = false)
	private PaymentMode paymode;
    
	

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public int getExpid() {
		return expid;
	}


	public void setExpid(int expid) {
		this.expid = expid;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getExpdesc() {
		return expdesc;
	}


	public void setExpdesc(String expdesc) {
		this.expdesc = expdesc;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getExptags() {
		return exptags;
	}


	public void setExptags(String exptags) {
		this.exptags = exptags;
	}
	


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Category getCats() {
		return cats;
	}


	public void setCats(Category cats) {
		this.cats = cats;
	}


	public PaymentMode getPaymode() {
		return paymode;
	}


	public void setPaymode(PaymentMode paymode) {
		this.paymode = paymode;
	}


	public String getCatcode() {
		return catcode;
	}


	public void setCatcode(String catcode) {
		this.catcode = catcode;
	}


	public int getPaycode() {
		return paycode;
	}


	public void setPaycode(int paycode) {
		this.paycode = paycode;
	}
    
	

	
} 



