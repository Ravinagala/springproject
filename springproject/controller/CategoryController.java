package img3.project.springproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import img3.project.springproject.entities.Category;
import img3.project.springproject.repo.CategoryRepo;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryRepo cr;
	@GetMapping("/getcategory")
	public List<Category> getCategory(){
		 return cr.findAll();
	}
}
