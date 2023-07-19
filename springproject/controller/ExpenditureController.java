package img3.project.springproject.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

import img3.project.springproject.entities.Expenditure;
import img3.project.springproject.repo.ExpenditureRepo;
import img3.project.springproject.repo.PaymentModeRepo;
import io.swagger.v3.oas.annotations.Operation;

@RestController
public class ExpenditureController {
	@Autowired
	ExpenditureRepo er;

	@GetMapping("/tags/{tag}")
	public List<Expenditure> getTags(@PathVariable("tag") String tags) {
		return er.findByExptagsContaining(tags);
	}

	@GetMapping("/amount/{month}")
	public List<String> getAmount(@PathVariable("month") int months) {
		return er.getTotalInMonth(months);
	}

	@GetMapping("/top5expenses/{sort}")
	@Operation(description = "Enter 1 for ascending order, 2 for descending order")
	public List<Expenditure> getTopExp(@PathVariable("sort") int sort) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		switch (sort) {
		case 1:
			return er.findByUser_Name(username, PageRequest.of(0, 5, Sort.by("amount")));
		case 2:
			return er.findByUser_Name(username, PageRequest.of(0, 5, Sort.by("amount").descending()));
		default:
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Enter 1 or 2");
		}

	}

	@DeleteMapping("/deleteExpenditure")
	public void deleteExp(@RequestParam("eid") int eid) {
		var v = er.findById(eid);
		if (v.isPresent()) {
			er.deleteById(eid);
		} else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data");
	}

	@PutMapping("/updateExpenditure")
	public Expenditure setExpId(@RequestParam("id") int id, @RequestParam("price") double price) {
		var exp = er.findById(id);
		if (exp.isPresent()) {
			var t = exp.get();
			t.setAmount(price);
			er.save(t);
			return t;

		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found ");
	}

	@GetMapping("/getexpensepage/{catcode}")
	public List<Expenditure> getListExpense(@PathVariable("catcode") String catcode) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var v = er.getExpenditureByUserAndCategorySortedById(username, catcode, PageRequest.of(0, 3, Sort.by("expid")));
		return v;
	}

	@GetMapping("/getexpenditurebypayment/{paymentname}")
	public List<Expenditure> getPayExpense(@PathVariable("paymentname") String paymentname) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var a = er.getExpenditureBypaymentname(username, paymentname,
				PageRequest.of(0, 3, Sort.by("expid").descending()));
		return a;
	}

	@GetMapping("/getexpenditurebydates/{mindate}/{maxdate}")
	@Operation(description = "please enter the date in yyyy-mm-dd format")
	public List<Expenditure> getDates(@PathVariable("mindate") Date min, @PathVariable("maxdate") Date max) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		var b = er.getExpenditureBetweenDates(username, min, max, PageRequest.of(0, 3, Sort.by("date").descending()));
		return b;
	}

	@Autowired
	PaymentModeRepo pay;

	@PostMapping("/addnewexpenditure")
	public Expenditure addExpenditure(@RequestBody Expenditure exp) {

		var payname = pay.getByPaycode(exp.getPaycode());
		if (exp.getUsername().compareTo(payname.getUsername()) == 0) {

			er.save(exp);
			return exp;
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username mismatch");
		}

	}

}
