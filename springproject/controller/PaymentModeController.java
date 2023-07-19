package img3.project.springproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import img3.project.springproject.entities.PaymentMode;
import img3.project.springproject.repo.PaymentModeRepo;

@RestController
public class PaymentModeController {
	
	@Autowired
	PaymentModeRepo pr;
	
	@PostMapping("/addnewpayment")
	public PaymentMode addPayment(@RequestBody PaymentMode pm)
	{
		 String username = SecurityContextHolder.getContext().getAuthentication().getName();
		 var paycode = pr.findById(pm.getPaycode());
		 if(paycode.isPresent()) {
			 throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"The paycode is already present");
		 }
		 else {
			 if(pm.getUsername().equals(username))
			 {
				 pr.save(pm);
				 return pm;
			 }
			 else
			 {
				 
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"username mismatch");
			 }
		 }
			 
	}	
	
	
	@DeleteMapping("/deletePayment")
	public void deletePay(@RequestParam("pid") int pid) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var v = pr.findById(pid);
		if(v.isPresent()) {
			var name= v.get();
			if(name.getUsername().equals(username))
			{
			pr.deleteById(pid);
		}
			else
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Username is mismatch");
			}
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Data not found");
		}
	}
	
	@PutMapping("/updatePayment/{code}/{name}")
	public void updatePay(@PathVariable("code") int code,@PathVariable("name") String payname)
	{
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var a = pr.findById(code);
		if(a.isPresent())
		{
			var c = a.get();
			if(c.getPuser().getName().equals(username)) {
				c.setPaymentname(payname);
				pr.save(c);
			}
			else
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"user mismatch");
				
		}
	}
	
	@GetMapping("/getpaymentmode")
	public List<String> getPayMode(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return pr.getPaymentModes(username);

	}
}
